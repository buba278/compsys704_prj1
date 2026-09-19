package digitaltwin;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Static mirror of the twin's active-product list, for Big-Picture's "Bottles
 * in Production" panel. Written by BigPictureTwinListener's background socket
 * thread, read by BigPictureCanvas's Swing paint thread -- same one-writer/
 * one-reader pattern as ConveyorState/RotaryTableState elsewhere in
 * machines/, so no synchronization is needed beyond the atomic reference
 * swap on update.
 */
public class BigPictureTwinState {

	public static final class ProductInfo {
		public final String productId;
		public final double volumeMl;
		public final double liquidRatio;
		public final String currentWorkstation;
		public final String status;
		public final boolean hasFault;
		public final Map<String, String> stationOutcomes;

		public ProductInfo(String productId, double volumeMl, double liquidRatio, String currentWorkstation,
				String status, boolean hasFault, Map<String, String> stationOutcomes) {
			this.productId = productId;
			this.volumeMl = volumeMl;
			this.liquidRatio = liquidRatio;
			this.currentWorkstation = currentWorkstation;
			this.status = status;
			this.hasFault = hasFault;
			this.stationOutcomes = stationOutcomes;
		}
	}

	private static volatile List<ProductInfo> activeProducts = Collections.emptyList();

	private BigPictureTwinState() {
	}

	public static void setActiveProducts(List<ProductInfo> products) {
		activeProducts = products;
	}

	public static List<ProductInfo> getActiveProducts() {
		return activeProducts;
	}
}
