package ba.ssst.edu;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CategorySalesReport {
    public static ArrayList<Map.Entry<String,Float>>  generate(ArrayList<Purchase> purchases) {
        Map<String,Float> map = new HashMap<>();
        for(Purchase purchase : purchases) {
            String cat = purchase.getItem().getCategory();
            float sales = purchase.getQuantity() * purchase.getItem().getPrice();
            map.put(cat,map.getOrDefault(cat,0f)+sales);
        }
        ArrayList<Map.Entry<String,Float>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> Float.compare(b.getValue(), a.getValue()));
        if(saveToFile("CategorySalesReport.txt",list)){
            System.out.println("CategorySalesReport.txt has been saved");
        }else{
            System.out.println("CategorySalesReport.txt could not be saved");
        }
        return list;

    }

    public static boolean saveToFile(String filename, ArrayList<Map.Entry<String,Float>> list) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            for(Map.Entry<String,Float> entry : list) {
                String cat = entry.getKey();
                float sales = entry.getValue();
                bw.write(cat + ": "+ String.format(Locale.US, "%.2f", sales));
                bw.newLine();

            }
            bw.close();
            return true;
        }catch(IOException e) { return false; }

    }
}
