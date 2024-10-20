import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.util.List;

public class Recv {

    private final static String QUEUE_NAME = "pizzashop";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            ObjectMapper objectMapper = new ObjectMapper();
            List<Pizza> pizzas = objectMapper.readValue(message.toString(), new TypeReference<List<Pizza>>() {});

            System.out.println("Deserializing Pizza object...");

            // Print the deserialized Pizza objects
            for (Pizza pizza : pizzas) {
                System.out.println("Pizza ID: " + pizza.getPizzaID());
                System.out.println("Pizza Name: " + pizza.getPizzaName());
                System.out.println("Pizza Price: " + pizza.getPizzaPrice());
                System.out.println("Pizza Size: " + pizza.getPizzaSize());
                System.out.println("Pizza Toppings: " + pizza.getPizzaToppings());
            }

        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}
