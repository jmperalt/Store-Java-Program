class SalesLineItem {

    private int quantity;

    // Reference ProductSpecification attribute
    private ProductSpecification productSpec;

    public SalesLineItem(){

    }
    public SalesLineItem (ProductSpecification spec, int quantity ) {
        this.productSpec = spec;
        this.quantity = quantity;
    }

    public float getSubtotal() {
        return (float) (productSpec.getPrice() * quantity);
    }

    public int getQuantity(){
        return quantity;
    }
    public ProductSpecification getProductSpec(){
        return productSpec;
    }
}