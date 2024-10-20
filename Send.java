import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

        private final static String QUEUE_NAME = "pizzashop";
        public static void main(String[] args) throws Exception {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");


            //Create pizza object
            Pizza myPizza = new Pizza(1000, "myPizza", 16.99, "Medium", "Bacon");

            //Creates list of pizza to parse into json payload
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

            List<Pizza> loadedPizzas = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("pizzas.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    loadedPizzas.add(Pizza.fromFixedFormatString(line));
                    System.out.println("Pizzas in flat file: " + line);
                }

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

            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = pizzaJson;
                // Read the flat file
                //byte[] fileData = Files.readAllBytes(Paths.get("/Users/jaysontroxel/IdeaProjects/Lab4Troxel/pizzas.txt"));

                // Publish the file data as a message
                //channel.basicPublish("", QUEUE_NAME, null, fileData);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" RabbitMQ Sent '" + message + "' to queue");

            }
        }
    }

