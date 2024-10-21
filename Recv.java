/** Project: Systems integration assignment (Solo lab 3)
 * Purpose Details: send/receive pizza flat file data/Json payloads using rabbitmq and web server
 * Course: IST242
 * Author: Jayson Troxel
 * Date Developed: 10/19/24
 * Last Date Changed: 10/20/24
 * Rev: 2
 */

//Import radditmq class
import com.rabbitmq.client.*;



public class Recv {

    //Rabbitmq queue name
    private final static String QUEUE_NAME = "pizzashop";

    /**
     * Main method receives rabbitmq flat file data and deserializes it and prints to the console
     * @param args program arguments
     * @throws Exception basic exception
     */
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //declare the rabbitmq channel
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");


            // Deserialize the message
            String[] parts = message.trim().split("\\s+");

            //Initializing deserialized pizza properties
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            double price = Double.parseDouble(parts[2]);
            String size = parts[3];
            String toppings = parts[4];

            //Create new pizza using deserialized information
            Pizza pizza = new Pizza(id, name, price, size, toppings);

            //Output deserialized pizza information
            System.out.println("Deserializing Pizza FLat File Data....");
            System.out.println("Pizza ID: " + pizza.getPizzaID());
            System.out.println("Pizza Name: " + pizza.getPizzaName());
            System.out.println("Pizza Price: " + pizza.getPizzaPrice());
            System.out.println("Pizza Size: " + pizza.getPizzaSize());
            System.out.println("Pizza Toppings: " + pizza.getPizzaToppings());

        };

        //Consume the message
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}

