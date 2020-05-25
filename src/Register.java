public class Register {
    private ProductCatalog catalog;
    private Sale sale;

    public Register(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    /**
     * Method that checks if the sale is complete
     * */
    public void endSale(){
        sale.becomeComplete();
    }
    public void enterItem(String id, int quantity ) {
        ProductSpecification spec = catalog.getSpecification(id);
        sale.makeLineItem(spec, quantity);
    }

    /**
     * When makeNewSale is invoke, then make a new instance of sale
     * */
    public void makeNewSale() {
        sale = new Sale();
    }
    public void makePayment(float cashTendered) {
        sale.makePayment(cashTendered);
    }
}