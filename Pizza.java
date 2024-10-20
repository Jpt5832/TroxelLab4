public class Pizza {


    private int pizzaID;
    private String pizzaName;
    private double pizzaPrice;
    private String pizzaToppings;
    private String pizzaSize;

public Pizza() {}

    public Pizza(int pizzaID, String pizzaName, double pizzaPrice, String pizzaSize, String pizzaToppings) {
        this.pizzaID = pizzaID;
        this.pizzaName = pizzaName;
        this.pizzaPrice = pizzaPrice;
        this.pizzaSize = pizzaSize;
        this.pizzaToppings = pizzaToppings;
    }

    public void setPizzaID(int pizzaID) {
        this.pizzaID = pizzaID;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public void setPizzaPrice(int pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public void setPizzaToppings( String pizzaToppings) {
        this.pizzaToppings = pizzaToppings;
    }
    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public int getPizzaID() {
        return pizzaID;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public String getPizzaToppings() {
        return pizzaToppings;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public String toFixedFormatString() {
        return String.format("%-7s%-10s%-10s%-10s%-40s", pizzaID, pizzaName, pizzaPrice, pizzaSize, pizzaToppings.toString());
    }

    public static Pizza fromFixedFormatString(String line) {
        String pizzaID = line.substring(0, 10).trim();
        String pizzaName = line.substring(10, 20).trim();
        String pizzaPrice = line.substring(20, 30).trim();
        String pizzaSize = line.substring(30, 40).trim();
        String pizzaToppings = line.substring(40, 50).trim();
        return new Pizza(1000, "myPizza", 16.99, "Medium", "Bacon");
    }
}

