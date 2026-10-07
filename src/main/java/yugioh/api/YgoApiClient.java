package yugioh.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.json.JSONObject;
import yugioh.model.Card;


/** Cliente de la API YGOProDeck: pide cartas al azar y las convierte en objetos Card. */
public class YgoApiClient {
    private static final String URL = "https://db.ygoprodeck.com/api/v7/randomcard.php";
    private static final int MAX_ATTEMPTS = 10;

    private final HttpClient client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    //Pide cartas hasta obtener un Monster con ATK y DEF válidos.
    public Card getRandomMonster() throws IOException, InterruptedException {
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IOException("La API respondió con código " + response.statusCode());
            }

            JSONObject json = new JSONObject(response.body())
                    .getJSONArray("data")
                    .getJSONObject(0);

            String type = json.optString("type", "");
            int atk = json.optInt("atk", -1);
            int def = json.optInt("def", -1);

            // Descarta cartas sin ATK o DEF
            if (type.contains("Monster") && atk >= 0 && def >= 0) {
                String name = json.getString("name");
                String imageUrl = json.getJSONArray("card_images")
                        .getJSONObject(0)
                        .getString("image_url_small");
                return new Card(name, atk, def, imageUrl);
            }
        }
        throw new IOException("No se pudo cargar la carta");
    }
}

