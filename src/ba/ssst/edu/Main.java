package ba.ssst.edu;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Purchase> purchases = new ArrayList<>();

        try {
        customers = ReadFromFile.processCustomers("Files/customers.csv",true);
        items = ReadFromFile.processItems("Files/items.csv",true);
        purchases = ReadFromFile.processPurchases("Files/purchases.csv",true);

        for(Purchase p:purchases){
            Item pItem = p.getItem();
            if(!items.contains(pItem)){
                System.out.println("ERROR: Item does not exist in inventory");
            }
            for(Item i : items){
                if(i == pItem) i.UpdateQuantity(p);
            }

        }

        System.out.println();

        //1
        System.out.println("TopSellingItemsRep");
        System.out.println(TopSellingItemsReport.generate(purchases));
        System.out.println();
        //2
            System.out.println("TopCustomerRep");
        System.out.println(TopCustomersReport.generate(purchases));
            System.out.println();
        //3
            System.out.println("CategorySalesRep");
        System.out.println(CategorySalesReport.generate(purchases));
            System.out.println();
        //4
            System.out.println("CustomerPurchaseHistryRep");

            for(Customer c : customers) {
                for(Purchase p : purchases){
                    if(p.getCustomer() == c){

                        System.out.println(c +"'s purchase history: ");
                        ArrayList<Map.Entry<String,Float>> list = CustomerPurchaseHistoryReport.generate(c,purchases);
                        System.out.println(c.getPurchases());
                        String editedName = c.getName().replace(" ", "");
                        String filename = editedName + "HistoryReport.txt";
                        if(CustomerPurchaseHistoryReport.saveToFile(filename,list)){
                            System.out.println(filename+ " has been saved");
                        }else{
                            System.out.println(filename+ " could not be saved");
                        }
                        System.out.println();
                    }
                }
            }
        //5
            System.out.println("InventoryRep");
        System.out.println(InventoryReport.generate(items));

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }


}