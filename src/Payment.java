public class Payment {
    private double amount;

    public Payment(){

    }
    public Payment(double tenderedAmount){
        amount = tenderedAmount;
    }

    public double getAmount()
    {
        return amount;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
}