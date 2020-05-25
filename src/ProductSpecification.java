public class ProductSpecification {
    private String id;
    private double price;
    private String description;


    /**
     * No-args constructor with default value*/
    public ProductSpecification() {
        id = "A000";
        description = "water_melon";
        price = 0.0;
    }

    public ProductSpecification (String description, double price) {
        this.price = price;
        this.description = description;
    }
    public ProductSpecification (String id, String description, double price) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

    /**
     * Returns the item id in the Product Specification
     *
     * @return the item of ID
     */
    public String getItemID(){
        return id;
    }

    /**
     * Sets the item code in the Item
     *
     * @param itemID
     */
    public void setItemID(String itemID) {
        this.id = itemID;
    }

    /**
     * Returns the item description in the Item
     *
     * @return the description of an item in product specification
     */
    public String getDescription() { return description; }
    /**
     * Sets the description in product specification
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Returns the price of product specification
     *
     * @return the price
     */
    public double getPrice() { return price; }

    /**
     * Sets the price of product specification
     *
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

}
