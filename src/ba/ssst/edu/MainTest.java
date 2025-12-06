package ba.ssst.edu;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class MainTest {

    private ArrayList<Purchase> setupMockPurchases() {
        ArrayList<Purchase> purchases = new ArrayList<>();

        Item shirt = new Item("T-Shirt", 15.0f, 100, "Apparel");
        Item pants = new Item("Jeans", 50.0f, 50, "Apparel");
        Item monitor = new Item("Monitor", 200.0f, 20, "Electronics");

        Customer alice = new Customer("Alice","gag");
        Customer bob = new Customer("Bob","ga2g");

        purchases.add(new Purchase(alice, shirt, 2));
        purchases.add(new Purchase(bob, monitor, 1));
        purchases.add(new Purchase(alice, pants, 3));
        purchases.add(new Purchase(bob, shirt, 1));

        return purchases;
    }
    private ArrayList<Item> setupMockInventory() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("T-Shirt", 15.0f, 100, "Apparel"));
        items.add(new Item("Jeans", 50.0f, 50, "Apparel"));
        items.add(new Item("Monitor", 200.0f, 20, "Electronics"));
        items.add(new Item("Keyboard", 70.0f, 0, "Electronics"));
        return items;
    }


    @Test
    public void Report_CategorySales_generate_CorrectTotalsAndOrder() {
        ArrayList<Purchase> purchases = setupMockPurchases();
        ArrayList<Map.Entry<String, Float>> result = CategorySalesReport.generate(purchases);

        assertEquals(2, result.size());

        assertEquals("Electronics", result.get(0).getKey());
        assertEquals(200.00f, result.get(0).getValue(), 0.01f);
        assertEquals("Apparel", result.get(1).getKey());
        assertEquals(195.00f, result.get(1).getValue(), 0.01f);
    }

    @Test
    public void Report_CustomerHistory_generate_CorrectAggregation() {
        ArrayList<Purchase> purchases = setupMockPurchases();
        Customer alice = new Customer("Alice","gag");
        ArrayList<Map.Entry<String, Float>> result = CustomerPurchaseHistoryReport.generate(alice, purchases);
        assertEquals(2, result.size());

        assertEquals("Jeans", result.get(0).getKey());
        assertEquals(150.00f, result.get(0).getValue(), 0.01f);
        assertEquals("T-Shirt", result.get(1).getKey());
        assertEquals(30.00f, result.get(1).getValue(), 0.01f);
    }

    @Test
    public void Report_Inventory_generate_CorrectOrderAndAggregation() {
        ArrayList<Item> inventory = setupMockInventory();
        ArrayList<Map.Entry<Item, Integer>> result = InventoryReport.generate(inventory);
        assertEquals(4, result.size());

        assertEquals("T-Shirt", result.get(0).getKey().getName());
        assertEquals((Integer) 100, result.get(0).getValue());
        assertEquals("Jeans", result.get(1).getKey().getName());
        assertEquals((Integer) 50, result.get(1).getValue());
    }


    @Test
    public void Report_TopCustomers_generate_CorrectOrderAndLimit() {
        ArrayList<Purchase> purchases = setupMockPurchases();
        ArrayList<Map.Entry<Customer, Float>> result = TopCustomersReport.generate(purchases);
        assertEquals(2, result.size());

        assertEquals("Bob", result.get(0).getKey().getName());
        assertEquals(215.00f, result.get(0).getValue(), 0.01f);
        assertEquals("Alice", result.get(1).getKey().getName());
        assertEquals(180.00f, result.get(1).getValue(), 0.01f);
    }

    @Test
    public void Report_TopSellingItems_generate_CorrectOrderAndLimit() {
        ArrayList<Purchase> purchases = setupMockPurchases();
        ArrayList<Map.Entry<Item, Integer>> result = TopSellingItemsReport.generate(purchases);
        assertEquals(3, result.size());

        assertTrue(result.get(0).getKey().getName().equals("T-Shirt") || result.get(0).getKey().getName().equals("Jeans"));
        assertTrue(result.get(1).getKey().getName().equals("T-Shirt") || result.get(1).getKey().getName().equals("Jeans"));

        assertEquals("Monitor", result.get(2).getKey().getName());
    }

    @Test
    public void Report_TopSellingItems_generate_LimitApplied() {
        ArrayList<Purchase> purchases = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            Item item = new Item("Item" + i, 10.0f, 10, "Gen");
            Customer c = new Customer("C","c@gmail.com");
            purchases.add(new Purchase(c, item, i+i));
        }

        ArrayList<Map.Entry<Item, Integer>> result = TopSellingItemsReport.generate(purchases);
        assertEquals(10, result.size());
    }

}
