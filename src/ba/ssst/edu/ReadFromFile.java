package ba.ssst.edu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadFromFile {
    private static Map<String,Customer> customerMap = new HashMap<>();
    private static Map<String,Item> itemMap = new HashMap<>();
    public static ArrayList<Item> processItems(String path, boolean hasHeader) throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);
        if (hasHeader) if(s.hasNextLine()) s.nextLine();

        ArrayList<Item> items = new ArrayList<>();

        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(",");

            if(parts.length != 4){
                System.out.println("ERROR: Wrong number of parameters: " + line);
                continue;
            }


            String name = parts[0].trim();
            if(name.isEmpty()) {
                System.out.println("ERROR: Emtpy name: " + line);
                continue;
            }

            Float price = 0f;
            try{
            price = Float.parseFloat(parts[1].trim());
            }catch(NumberFormatException e){
                System.out.println("ERROR: Wrong format of quantity: "  + line);
                continue;
            }

            int quantity = 0;
            try {
                quantity = Integer.parseInt(parts[2].trim());
            }catch(NumberFormatException e){
                //throw new IllegalArgumentException("ERROR: Wrong format of quantity: "  + line);
                System.out.println("ERROR: Wrong format of quantity: "  + line);
                continue;
            }
            if(quantity <= 0){
                //throw new IllegalArgumentException("Quantity must be bigger than zero: "  + line);
                System.out.println("ERROR: Quantity must be bigger than zero: "  + line);
                continue;
            }

            String category = parts[3].trim();

            Item i = new Item(name, price, quantity, category);
            items.add(i);
            itemMap.put(name,i);
        }

        return  items;
    }
    public static ArrayList<Customer> processCustomers(String path, boolean hasHeader) throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);
        if (hasHeader) if(s.hasNextLine()) s.nextLine();

        ArrayList<Customer> customers = new ArrayList<>();

        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(",");
            if(parts.length != 2){
                System.out.println("ERROR: Wrong number of parameters: " + line);
                continue;
            }
            String name = parts[0].trim();
            if(name.isEmpty()) {
                System.out.println("ERROR: Emtpy name: " + line);
                continue;
            }
            String email = parts[1].trim();

            Customer c = new Customer(name, email);
            customers.add(c);
            customerMap.put(name,c);
        }

        return customers;
    }
    public static ArrayList<Purchase> processPurchases(String path, boolean hasHeader) throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);
        if (hasHeader) if(s.hasNextLine()) s.nextLine();

        ArrayList<Purchase> purchases= new ArrayList<>();

        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(",");




            if(parts.length != 3){
               // throw new IllegalStateException("Wrong number of parameters: " + line);
                System.out.println("ERROR: Wrong number of parameters: " + line);
                continue;
            }

            String customerName = parts[0].trim();
            Customer customer = customerMap.get(customerName);
            if (customer == null) {
                //throw new IllegalStateException("Customer " + customerName + " not found: " + line);
                System.out.println("ERROR: Customer " + customerName + " not found: " + line);
                continue;
            }

            String itemName = parts[1].trim();
            Item item = itemMap.get(itemName);
            if(item == null){
                //throw new IllegalStateException("Item " + itemName + " not found: " + line);
                System.out.println("ERROR: Item " + itemName + " not found: " + line);
                continue;
            }

            int quantity = 0;
            try {
            quantity = Integer.parseInt(parts[2].trim());
            }catch(NumberFormatException e){
                //throw new IllegalArgumentException("ERROR: Wrong format of quantity: "  + line);
                System.out.println("ERROR: Wrong format of quantity: "  + line);
                continue;
            }
            if(quantity <= 0){
                //throw new IllegalArgumentException("Quantity must be bigger than zero: "  + line);
                System.out.println("ERROR: Quantity must be bigger than zero: "  + line);
                continue;
            }


            Purchase p = new Purchase(customer,item,quantity);
            purchases.add(p);
            customer.AddPurchase(p);



        }
        System.out.println(customerMap.toString());
        System.out.println(itemMap.toString());
        return purchases;
    }
}
