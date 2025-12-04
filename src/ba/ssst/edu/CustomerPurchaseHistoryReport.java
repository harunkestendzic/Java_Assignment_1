package ba.ssst.edu;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CustomerPurchaseHistoryReport {
    public static ArrayList<Map.Entry<String,Float>> generate(Customer c,ArrayList<Purchase>  purchases){
        Map<String,Float> map = new HashMap<>();
        for(Purchase p:purchases){
            if(p.getCustomer().equals(c)){
                Item item = p.getItem();
                String itemName = item.getName();
                Float price = item.getPrice() * p.getQuantity();
                map.put(itemName,map.getOrDefault(itemName,0f) + price);
            }
        }
        ArrayList<Map.Entry<String,Float>> list = new ArrayList<>(map.entrySet());
        list.sort((a,b) -> b.getValue().compareTo(a.getValue()));
        return list;
    }

    public static boolean saveToFile(String filename, ArrayList<Map.Entry<String,Float>> list) {
        try {
            FileWriter fw = new FileWriter(filename);
            for(Map.Entry<String,Float> entry : list) {
                String itemName = entry.getKey();
                float amount = entry.getValue();
                fw.write(itemName + ": "  + String.format(Locale.US, "%.2f", amount) + "\n");

            }
            fw.close();
            return true;
        }catch(IOException e) { return false; }

    }

}
