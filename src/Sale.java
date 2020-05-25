import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Sale {
    private List<SalesLineItem> saleLineItems = new ArrayList<>();

    // Data and Time
    private Date date = new Date();
    private boolean isComplete = false;
    private Payment payment;

    public double getBalance()
    {
        return payment.getAmount() - getTotal();
    }
    public void becomeComplete() {
        isComplete = true;
    }
    public boolean isComplete() {
        return isComplete;
    }
    public void makeLineItem (ProductSpecification spec, int quantity) {
        saleLineItems.add(new SalesLineItem( spec, quantity ) );
    }
    public float getTotal() {
        float total = 0;
        Iterator it = saleLineItems.iterator();
        while (it.hasNext()) {
            SalesLineItem sli = (SalesLineItem) it.next();
            total =+ sli.getSubtotal();
        }
        return total;
    }
    public void makePayment(float tenderedAmount ) {
        payment = new Payment(tenderedAmount);
    }
    public Date getDateCreated() {
        return date;
    }
}

