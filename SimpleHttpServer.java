
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class SimpleHttpServer {
        public static void main(String[] args) throws Exception {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/hello", new MyHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        }

        static class MyHandler implements HttpHandler {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                Pizza myPizza = new Pizza(1000, "myPizza", 16.99, "Medium", "Bacon");
                List<Pizza> pizzas = new ArrayList<>();
                pizzas.add(myPizza);

                //Serialize it to flat file
                try (PrintWriter writer = new PrintWriter(new FileWriter("pizzas.txt"))) {

                    for (Pizza pizza : pizzas) {
                        writer.println(pizza.toFixedFormatString());
                    }
                    System.out.println("New pizza added to flat file!");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Serializes pizza object into JSON payload
                ObjectMapper objectMapper = new ObjectMapper();
                String pizzaJson = null;
                try {
                    pizzaJson = objectMapper.writeValueAsString(pizzas);
                    System.out.println("Pizza object serialized to JSON string:");
                    System.out.println(pizzaJson);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                String response = pizzaJson;
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

