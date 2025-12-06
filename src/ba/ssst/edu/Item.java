package ba.ssst.edu;

import java.util.Objects;

public class Item implements Comparable<Item> {
    public Item(String name, float price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }


    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    private String name;
    private float price;
    private Integer quantity;
    private String category;

    @Override
    public String toString() {
        return name;
        //return "Name: " + name + ", Price: " + price + ", Quantity: " + quantity + ", Category: " + category;
    }
    @Override
    public int compareTo(Item o) {
        return this.quantity.compareTo(o.quantity);
    }


    public void UpdateQuantity(Purchase p) {
        int pQ = p.getQuantity();
        if(this.quantity - pQ < 0 ){
            System.out.println("Error: Purchase ["+p+"] quantity exceeded, ("+this.quantity+" items in stock)");
        }else this.quantity -= pQ;
    }
}
