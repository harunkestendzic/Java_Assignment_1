package ba.ssst.edu;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TopSellingItemsReport {
    public static ArrayList<Map.Entry<Item,Integer>> generate(ArrayList<Purchase> purchases) {
        Map<Item, Integer> map = new HashMap<>();
        for (Purchase purchase : purchases) {
        Item item = purchase.getItem();
        int quantity = purchase.getQuantity();

        map.put(item, map.getOrDefault(item, 0) + quantity);
        }
        ArrayList<Map.Entry<Item,Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.<Item,Integer>comparingByValue().reversed());
        //list.sort((a,b) -> b.getValue() - a.sgetValue());
        int end = Math.min(list.size(), 10);
        ArrayList<Map.Entry<Item,Integer>> top10 = new ArrayList<>(list.subList(0, end));

        if(saveToFile("TopSellingItemsReport.txt",top10)){
            System.out.println("TopSellingItemsReport.txt has been saved");
        }else{
            System.out.println("TopSellingItemsReport.txt could not be saved");
        }
        return top10;
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
