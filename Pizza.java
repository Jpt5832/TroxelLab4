/** Project: Systems integration assignment (Solo lab 3)
 * Purpose Details: send/receive pizza flat file data/Json payloads using rabbitmq and web server
 * Course: IST242
 * Author: Jayson Troxel
 * Date Developed: 10/19/24
 * Last Date Changed: 10/20/24
 * Rev: 2
 */

public class Pizza {

    //Pizza object properties
    private int pizzaID; //Pizza ID
    private String pizzaName; //Name of pizza
    private double pizzaPrice; //Price of pizza
    private String pizzaToppings; //toppings on the pizza
    private String pizzaSize; //size of pizza

    //Empty constructor
    public Pizza() {}

    //Pizza constructor
    public Pizza(int pizzaID, String pizzaName, double pizzaPrice, String pizzaSize, String pizzaToppings) {
        this.pizzaID = pizzaID;
        this.pizzaName = pizzaName;
        this.pizzaPrice = pizzaPrice;
        this.pizzaSize = pizzaSize;
        this.pizzaToppings = pizzaToppings;
    }

    /**
     * Sets pizza id
     * @param pizzaID pizza id number
     */
    public void setPizzaID(int pizzaID) {
        this.pizzaID = pizzaID;
    }

    /**
     * Sets pizza name
     * @param pizzaName pizza name
     */
    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    /**
     * Sets pizza price
     * @param pizzaPrice pizza's price
     */
    public void setPizzaPrice(int pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    /**
     * Sets pizza toppings
     * @param pizzaToppings pizza toppings
     */
    public void setPizzaToppings( String pizzaToppings) {
        this.pizzaToppings = pizzaToppings;
    }

    /**
     * Sets pizza size
     * @param pizzaSize pizza isize
     */
    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    /**
     * Gets pizza ID number
     * @return pizzaID
     */
    public int getPizzaID() {
        return pizzaID;
    }

    /**
     * Gets pizza name
     * @return pizzaName
     */
    public String getPizzaName() {
        return pizzaName;
    }

    /**
     * Gets pizza price
     * @return pizzaPrice
     */
    public double getPizzaPrice() {
        return pizzaPrice;
    }

    /**
     * Gets pizza toppings
     * @return pizzaToppings
     */
    public String getPizzaToppings() {
        return pizzaToppings;
    }

    /**
     * Gets pizza size
     * @return pizzaSize
     */
    public String getPizzaSize() {
        return pizzaSize;
    }

    /**
     * Takes pizza information and returns it in a formatted string
     * @return formatted string
     */
    public String toFixedFormatString() {
        return String.format("%-7s%-10s%-10s%-10s%-20s", pizzaID, pizzaName, pizzaPrice, pizzaSize, pizzaToppings.toString());
    }

    /**
     * Takes a whole string and trims it to get pizza properties
     * @param line string to be trimmed
     * @return new pizza object
     */
    public static Pizza fromFixedFormatString(String line) {
        String pizzaID = line.substring(0, 10).trim();
        String pizzaName = line.substring(10, 20).trim();
        String pizzaPrice = line.substring(20, 30).trim();
        String pizzaSize = line.substring(30, 40).trim();
        String pizzaToppings = line.substring(40, 50).trim();
        return new Pizza(1000, "myPizza", 16.99, "Medium", "Bacon, pepperoni");
    }
}

