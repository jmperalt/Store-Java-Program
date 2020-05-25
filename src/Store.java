public class Store {
    private ProductCatalog catalog = new ProductCatalog();
    private String address;
    private String name;

    private Register register = new Register(catalog);

    public Register getRegister() {
        return register;
    }

    public String getAddress(){
        return address;
    }

    public Sale addSale(Sale sale){
        return sale;
    }
}