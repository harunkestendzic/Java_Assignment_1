package ba.ssst.edu;
public class Purchase{
    public Purchase(Customer customer, Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    private Item item;
    private Integer quantity;

    private Customer customer;
    public Customer getCustomer() {
        return customer;
    }
    @Override
    public String toString() {
        return item.getName() +" (" + quantity + ") "+ "bought by " + customer + " for " + item.getPrice()*quantity;

    }

}
