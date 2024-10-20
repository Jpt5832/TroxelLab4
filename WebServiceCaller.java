import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class WebServiceCaller {
    public static void main(String[] args) {
        try {
            // Specify the URL of the web service
            String url = "http://localhost:8000/hello";

            // Create a URL object
            URL obj = new URL(url);

            // Open a connection to the URL
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set the request method
            con.setRequestMethod("GET");

            // Set request headers if needed
            // con.setRequestProperty("Content-Type", "application/json");

            // Get the response code
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            // Read the response from the web service
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response
            System.out.println("Response: " + response.toString());

            // Parse the JSON response as needed
            ObjectMapper objectMapper = new ObjectMapper();
            List<Pizza> pizzas = objectMapper.readValue(response.toString(), new TypeReference<List<Pizza>>() {});

            System.out.println("Deserializing Pizza object...");

            // Print the deserialized Pizza objects
            for (Pizza pizza : pizzas) {
                System.out.println("Pizza ID: " + pizza.getPizzaID());
                System.out.println("Pizza Name: " + pizza.getPizzaName());
                System.out.println("Pizza Price: " + pizza.getPizzaPrice());
                System.out.println("Pizza Size: " + pizza.getPizzaSize());
                System.out.println("Pizza Toppings: " + pizza.getPizzaToppings());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
