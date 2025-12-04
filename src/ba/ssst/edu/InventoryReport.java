package ba.ssst.edu;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InventoryReport {
    public static ArrayList<Map.Entry<Item,Integer>> generate(ArrayList<Item> items) {
        Map<Item, Integer> map = new HashMap<>();
        for (Item item : items) {
            map.put(item, map.getOrDefault(item, 0) + item.getQuantity());
        }
        ArrayList<Map.Entry<Item,Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a,b) -> b.getValue() - a.getValue());
        if(saveToFile("InventoryReport.txt", list)){
            System.out.println("InventoryReport.txt has been saved");
        }else{
            System.out.println("InventoryReport.txt could not be saved");
        }
        return list;
    }

    public static boolean saveToFile(String filename, ArrayList<Map.Entry<Item,Integer>> list) {
        try {
            FileWriter fw = new FileWriter(filename);
            for(Map.Entry<Item,Integer> entry : list) {
                Item item = entry.getKey();
                int quantity = entry.getValue();
                fw.write(item.getName() + ": "+ quantity + "\n");

            }
            fw.close();
            return true;
        }catch(IOException e) { return false; }

    }
}
