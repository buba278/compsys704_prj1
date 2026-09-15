package machines.pos;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

// The order-submission form + queue list, shared verbatim between the individual Pos
// window (PosPanel) and the Big-Picture window, so "submit an order from Big Picture"
// behaves exactly like the individual Pos window - same fields, same submit path
// (OrderQueue.enqueue(), which now routes through the hub/client sync - see
// OrderQueue), same queue rendering, just embedded in a different frame.
public class PosFormPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    // ── form state (updated by radio buttons, read on submit) ──────────────
    private int selectedRatio    = 50;
    private int selectedVolume   = 330;
    private int selectedQuantity = 3;

    // ── queue display ───────────────────────────────────────────────────────
    private final DefaultListModel<Order> listModel = new DefaultListModel<>();
    private final JList<Order> orderList = new JList<>(listModel);

    // ── helpers ─────────────────────────────────────────────────────────────

    private JPanel radioGroup(String title, int[] values, String suffix,
                              int defaultVal, RadioSetter setter) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(title));
        ButtonGroup group = new ButtonGroup();
        for (int v : values) {
            JRadioButton rb = new JRadioButton(v + suffix);
            final int val = v;
            rb.addActionListener(e -> setter.set(val));
            if (v == defaultVal) {
                rb.setSelected(true);
                setter.set(defaultVal);
            }
            group.add(rb);
            panel.add(rb);
        }
        return panel;
    }

    @FunctionalInterface
    interface RadioSetter { void set(int v); }

    // ── constructor ─────────────────────────────────────────────────────────

    public PosFormPanel() {
        JPanel ratioPanel    = radioGroup("Liquid A ratio",  new int[]{30, 50, 70},    "% A",      50,  v -> selectedRatio    = v);
        JPanel volumePanel   = radioGroup("Bottle size",     new int[]{200, 330, 500}, " ml",      330, v -> selectedVolume   = v);
        JPanel quantityPanel = radioGroup("Batch quantity",  new int[]{1, 2, 3, 5},   " bottles", 3,   v -> selectedQuantity = v);

        JButton submit = new JButton("Submit order");
        submit.addActionListener(e -> {
            Order order = new Order(selectedRatio, selectedVolume, selectedQuantity);
            OrderQueue.enqueue(order);
        });

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0; form.add(ratioPanel, c);
        c.gridy = 1; form.add(volumePanel, c);
        c.gridy = 2; form.add(quantityPanel, c);
        c.gridy = 3; form.add(submit, c);
        form.setBorder(BorderFactory.createTitledBorder("Purchase Order"));

        // ── queue panel ──────────────────────────────────────────────────────
        orderList.setCellRenderer(new OrderCellRenderer());
        orderList.setSelectionModel(new javax.swing.DefaultListSelectionModel() {
            @Override public void setSelectionInterval(int i0, int i1) {} // non-selectable
        });
        JScrollPane scroll = new JScrollPane(orderList);
        scroll.setPreferredSize(new Dimension(260, 200));
        JPanel queuePanel = new JPanel(new BorderLayout());
        queuePanel.setBorder(BorderFactory.createTitledBorder("Order Queue"));
        queuePanel.add(scroll, BorderLayout.CENTER);

        setLayout(new BorderLayout(0, 8));
        add(form, BorderLayout.NORTH);
        add(queuePanel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }

    // ── repaint loop (call periodically from the owning window's own loop) ──────

    public void refresh() {
        List<Order> snapshot = OrderQueue.getOrders();
        // only append genuinely new orders — never call set() on existing ones,
        // since that fires ListDataEvents that cause cells to flicker/pulse.
        // The renderer reads Order's volatile fields directly, so repaint() alone suffices.
        int n = snapshot.size();
        for (int i = listModel.size(); i < n; i++) {
            listModel.addElement(snapshot.get(i));
        }
        orderList.repaint();
    }

    // ── cell renderer ────────────────────────────────────────────────────────

    static class OrderCellRenderer extends JPanel implements ListCellRenderer<Order> {

        private static final Color DONE_GREEN    = new Color(34, 150, 60);
        private static final Color QUEUED_GREY   = new Color(160, 160, 160);
        private static final Color ACTIVE_TEXT   = new Color(30, 30, 30);
        private static final Font  CELL_FONT     = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

        private final JLabel iconLabel = new JLabel();
        private final JLabel textLabel = new JLabel();

        OrderCellRenderer() {
            setLayout(new BorderLayout(8, 0));
            textLabel.setFont(CELL_FONT);
            // fixed width so queued items (no icon) align with in-progress/done items
            iconLabel.setPreferredSize(new Dimension(SpinnerIcon.SIZE, SpinnerIcon.SIZE));
            iconLabel.setMinimumSize(new Dimension(SpinnerIcon.SIZE, SpinnerIcon.SIZE));
            add(iconLabel, BorderLayout.WEST);
            add(textLabel, BorderLayout.CENTER);
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Order> list,
                Order order, int index, boolean isSelected, boolean hasFocus) {
            String base = String.format("#%d  %d%% A  %d ml  ×%d",
                    order.number, order.ratioA, order.volume, order.quantity);

            switch (order.status) {
                case DONE:
                    textLabel.setText(order.completionMs >= 0
                            ? base + "  (" + order.completionMs + " ms)"
                            : base);
                    textLabel.setForeground(DONE_GREEN);
                    iconLabel.setIcon(new CheckIcon());
                    break;
                case IN_PROGRESS:
                    textLabel.setText(base);
                    textLabel.setForeground(ACTIVE_TEXT);
                    iconLabel.setIcon(new SpinnerIcon());
                    break;
                default: // QUEUED
                    textLabel.setText(base);
                    textLabel.setForeground(QUEUED_GREY);
                    iconLabel.setIcon(null);
                    break;
            }

            setOpaque(true);
            setBackground(Color.WHITE);
            return this;
        }
    }

    // ── icons ─────────────────────────────────────────────────────────────────

    static class SpinnerIcon implements Icon {
        static final int SIZE = 14;

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            int angle = (int) ((System.currentTimeMillis() / 4) % 360);
            // dim trail
            g2.setColor(new Color(180, 210, 255));
            g2.drawArc(x + 1, y + 1, SIZE - 2, SIZE - 2, angle + 270, 90);
            // bright head
            g2.setColor(new Color(50, 120, 240));
            g2.drawArc(x + 1, y + 1, SIZE - 2, SIZE - 2, angle, 270);
            g2.dispose();
        }

        @Override public int getIconWidth()  { return SIZE; }
        @Override public int getIconHeight() { return SIZE; }
    }

    static class CheckIcon implements Icon {
        private static final int SIZE = 14;

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(34, 150, 60));
            g2.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawLine(x + 2,  y + 7,  x + 5, y + 11);
            g2.drawLine(x + 5,  y + 11, x + 12, y + 3);
            g2.dispose();
        }

        @Override public int getIconWidth()  { return SIZE; }
        @Override public int getIconHeight() { return SIZE; }
    }
}
