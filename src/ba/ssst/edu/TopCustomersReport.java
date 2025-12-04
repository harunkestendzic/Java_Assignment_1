package ba.ssst.edu;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TopCustomersReport {
    public static ArrayList<Map.Entry<Customer,Float>> generate(ArrayList<Purchase> purchases){
        Map<Customer, Float> map = new HashMap<>();
        for (Purchase purchase : purchases) {
            Customer c =purchase.getCustomer();
            Float amount = purchase.getQuantity() * purchase.getItem().getPrice();
            map.put(c,map.getOrDefault(c, Float.valueOf(0))+amount);
        }
        ArrayList<Map.Entry<Customer,Float>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> Float.compare(b.getValue(), a.getValue()));
        int end = Math.min(list.size(), 10);
        ArrayList<Map.Entry<Customer,Float>> top10 = new ArrayList<>(list.subList(0, end));

        if(saveToFile("TopCustomersReport.txt",top10)){
            System.out.println("TopCustomersReport.txt has been saved");
        }else{
            System.out.println("TopCustomersReport.txt could not be saved");
        }
        return top10;
    }

    public static boolean saveToFile(String filename, ArrayList<Map.Entry<Customer,Float>> list) {
        try {
            FileWriter fw = new FileWriter(filename);
            for(Map.Entry<Customer,Float> entry : list) {
                Customer customer = entry.getKey();
                Float amount = entry.getValue();
                fw.write(customer + ": "+  String.format(Locale.US, "%.2f", amount) + "\n");

            }
            fw.close();
            return true;
        }catch(IOException e) { return false; }

    }
}
