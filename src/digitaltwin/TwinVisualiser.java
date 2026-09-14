package digitaltwin;

import java.awt.EventQueue;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TwinVisualiser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel batchLabel;
    private JLabel progressLabel;
    private JLabel rejectedLabel;
    private JLabel activeLabel;

    // Station UI
    private JPanel stationsContainer;
    private Map<String, StationPanel> stationPanels = new HashMap<>();
    
    // Product Table
    private DefaultTableModel tableModel;
    private JTable productTable;

    private static final Gson GSON = new Gson();

    public TwinVisualiser() {
        setTitle("ABS Digital Twin Visualiser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 244, 248)); // Light, clean background

        initHeader();
        initStations();
        initProductTable();

        setLocationRelativeTo(null);
    }

    private void initHeader() {
        JPanel headerPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        headerPanel.setOpaque(false);

        batchLabel = createStatPanel(headerPanel, "Batch", "—");
        progressLabel = createStatPanel(headerPanel, "Completed", "0 / 0");
        rejectedLabel = createStatPanel(headerPanel, "Rejected", "0");
        activeLabel = createStatPanel(headerPanel, "On Line", "0");

        add(headerPanel, BorderLayout.NORTH);
    }

    private JLabel createStatPanel(JPanel parent, String title, String defaultVal) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        JLabel titleLbl = new JLabel(title, SwingConstants.CENTER);
        titleLbl.setForeground(Color.GRAY);
        JLabel valLbl = new JLabel(defaultVal, SwingConstants.CENTER);
        valLbl.setFont(new Font("Monospaced", Font.BOLD, 22));
        
        panel.add(titleLbl, BorderLayout.SOUTH);
        panel.add(valLbl, BorderLayout.CENTER);
        parent.add(panel);
        return valLbl;
    }

    private void initStations() {
        stationsContainer = new JPanel(new GridLayout(1, 7, 10, 0));
        stationsContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        stationsContainer.setOpaque(false);

        // Station IDs must exactly match the stationId each PlantTwin subclass
        // passes to super(stationId, stationName) -- confirmed against your
        // uploaded CapperPlantTwin ("cap-screwer") and labellerController.sysj's
        // TwinClient ("labeller"). The other five are my best guess based on
        // the naming convention -- double check them against whatever your
        // actual Loading Conveyor / Rotary Table / Filler / Lid Placer / Sorter
        // twin classes pass to super(...), and fix any that don't match.
        String[] stationIds = {
            "loading-conveyor", "rotary-table", "filler", "lid-placer", "cap-screwer", "labeller", "sorter"
        };
        String[] stationNames = {
            "Loading Conveyor", "Rotary Table", "Filler", "Lid Placer", "Cap Screwer", "Labeller", "Sorter"
        };

        for (int i = 0; i < stationIds.length; i++) {
            StationPanel sp = new StationPanel(stationNames[i]);
            stationPanels.put(stationIds[i], sp); // Map by ID for fast lookup during JSON parsing
            stationsContainer.add(sp);
        }

        add(stationsContainer, BorderLayout.CENTER);
    }

    private void initProductTable() {
        String[] columns = {"Product ID", "Position", "Volume", "Ratio", "Status", "Last Outcome"};
        tableModel = new DefaultTableModel(columns, 0);
        productTable = new JTable(tableModel);
        productTable.setRowHeight(24);
        productTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        scrollPane.setPreferredSize(new Dimension(1000, 250));
        
        add(scrollPane, BorderLayout.SOUTH);
    }

    /**
     * Call this method from your TCP Socket Listener whenever new JSON arrives.
     */
    public void updateDashboard(String jsonString) {
        try {
            JsonObject root = GSON.fromJson(jsonString, JsonObject.class); 

            // 1. Update Header Stats
            String batchId = root.has("batchId") && !root.get("batchId").isJsonNull() ? root.get("batchId").getAsString() : "—";
            int completed = root.get("batchCompletedCount").getAsInt();
            int target = root.get("batchTargetCount").getAsInt();
            int rejected = root.get("rejectedCount").getAsInt();
            
            JsonArray activeProducts = root.getAsJsonArray("activeProducts");
            
            batchLabel.setText(batchId);
            progressLabel.setText(completed + " / " + target);
            rejectedLabel.setText(String.valueOf(rejected));
            activeLabel.setText(String.valueOf(activeProducts.size()));

            // 2. Update Stations
            JsonArray plantTwins = root.getAsJsonArray("plantTwins");
            for (StationPanel sp : stationPanels.values()) {
                sp.setState("IDLE"); // Reset all to idle first
            }
            for (JsonElement element : plantTwins) {
                JsonObject twin = element.getAsJsonObject();
                String id = twin.get("stationId").getAsString();
                String state = twin.get("state").getAsString();
                
                if (stationPanels.containsKey(id)) {
                    stationPanels.get(id).setState(state);
                }
            }

            // 3. Update Product Table
            tableModel.setRowCount(0); // Clear existing rows
            for (JsonElement element : activeProducts) {
                JsonObject prod = element.getAsJsonObject();
                
                String pId = prod.get("productId").getAsString().substring(0, 8); // Short ID
                String pos = prod.get("currentPosition").getAsString() + " / 7";
                String vol = prod.has("volumeMl") ? prod.get("volumeMl").getAsString() + " ml" : "—";
                String ratio = prod.has("liquidRatio") ? prod.get("liquidRatio").getAsString() : "—";
                String status = prod.get("status").getAsString();
                
                // Extract last outcome from the Map
                String lastOutcome = "—";
                if (prod.has("stationOutcomes")) {
                    JsonObject outcomes = prod.getAsJsonObject("stationOutcomes");
                    for (String key : outcomes.keySet()) {
                        lastOutcome = outcomes.get(key).getAsString(); // Gets the last one in insertion order
                    }
                }

                tableModel.addRow(new Object[]{pId, pos, vol, ratio, status, lastOutcome});
            }

        } catch (Exception e) {
            System.err.println("Failed to parse visualizer JSON: " + e.getMessage());
        }
    }

    /**
     * Inner class representing a single physical station's UI block.
     */
    private class StationPanel extends JPanel {
        private JLabel nameLabel;
        private JPanel indicator;

        public StationPanel(String name) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

            indicator = new JPanel();
            indicator.setPreferredSize(new Dimension(50, 50));
            indicator.setMaximumSize(new Dimension(50, 50));
            indicator.setBackground(Color.LIGHT_GRAY);
            indicator.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            indicator.setAlignmentX(Component.CENTER_ALIGNMENT);

            nameLabel = new JLabel("<html><center>" + name.replace(" ", "<br>") + "</center></html>");
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));

            add(indicator);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(nameLabel);
        }

        public void setState(String state) {
            switch (state) {
                case "IN_PROGRESS":
                    indicator.setBackground(new Color(227, 167, 59)); // Progress Yellow
                    break;
                case "ERROR":
                    indicator.setBackground(new Color(224, 82, 92)); // Error Red
                    break;
                default:
                    indicator.setBackground(Color.LIGHT_GRAY); // Idle Gray
                    break;
            }
            indicator.repaint();
        }
    }

    public static void main(String[] args) {
        // Run UI creation on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            TwinVisualiser app = new TwinVisualiser();
            app.setVisible(true);

            // Optional: Feed a dummy JSON string here to test the layout immediately
            TwinListener listener = new TwinListener();
            listener.connectToTwin("127.0.0.1", 8080, app);
        });
    }
    

}