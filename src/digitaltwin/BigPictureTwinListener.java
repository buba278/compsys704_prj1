package digitaltwin;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Feeds BigPictureTwinState.activeProducts from TwinServer's visualiser push
 * socket -- the same feed TwinVisualiser/TwinListener already consume (see
 * TwinVisualiser.updateDashboard() for the JSON shape this mirrors). Kept
 * separate from TwinListener since that class is wired to a TwinVisualiser
 * Swing object specifically, not a plain static state holder.
 *
 * Best-effort like TwinClient: if TwinServer isn't running, Big-Picture must
 * keep working with an empty "Bottles in Production" list rather than
 * failing to start.
 */
public class BigPictureTwinListener {

	private static final Gson GSON = new Gson();

	private BigPictureTwinListener() {
	}

	public static void start(String host, int port) {
		Thread t = new Thread(() -> connectLoop(host, port));
		t.setDaemon(true);
		t.start();
	}

	private static void connectLoop(String host, int port) {
		while (true) {
			try (Socket socket = new Socket(host, port);
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
				String jsonLine;
				while ((jsonLine = in.readLine()) != null) {
					applyJson(jsonLine);
				}
			} catch (Exception e) {
				// TwinServer not running / connection dropped -- retry below.
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException ignored) {
			}
		}
	}

	private static void applyJson(String jsonLine) {
		try {
			JsonObject root = GSON.fromJson(jsonLine, JsonObject.class);
			JsonArray activeProducts = root.getAsJsonArray("activeProducts");

			List<BigPictureTwinState.ProductInfo> products = new ArrayList<>();
			for (JsonElement element : activeProducts) {
				JsonObject prod = element.getAsJsonObject();

				String productId = prod.get("productId").getAsString();
				double volumeMl = prod.has("volumeMl") && !prod.get("volumeMl").isJsonNull()
						? prod.get("volumeMl").getAsDouble() : 0;
				double liquidRatio = prod.has("liquidRatio") && !prod.get("liquidRatio").isJsonNull()
						? prod.get("liquidRatio").getAsDouble() : 0;
				String workstation = prod.has("currentWorkstation") && !prod.get("currentWorkstation").isJsonNull()
						? prod.get("currentWorkstation").getAsString() : "-";
				String status = prod.has("status") && !prod.get("status").isJsonNull()
						? prod.get("status").getAsString() : "ON_LINE";
				boolean hasFault = prod.has("hasFault") && prod.get("hasFault").getAsBoolean();

				Map<String, String> outcomes = new LinkedHashMap<>();
				if (prod.has("stationOutcomes")) {
					JsonObject oc = prod.getAsJsonObject("stationOutcomes");
					for (String key : oc.keySet()) {
						outcomes.put(key, oc.get(key).getAsString());
					}
				}

				products.add(new BigPictureTwinState.ProductInfo(
						productId, volumeMl, liquidRatio, workstation, status, hasFault, outcomes));
			}

			BigPictureTwinState.setActiveProducts(products);
		} catch (Exception e) {
			// malformed/partial line -- skip, next push arrives in 100ms
		}
	}
}
