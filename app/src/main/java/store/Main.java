package store;

import store.models.*;
import store.services.*;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Store store = new Store();
    InventoryService inventoryService = new InventoryService(store);
    SalesService salesService = new SalesService(store);

    // Adding products
    Product product1 = new Product(1, "Milk", 1.0, "food", LocalDate.now().plusDays(7), 50);
    Product product2 = new Product(2, "Soap", 0.5, "non-food", LocalDate.now().plusMonths(3), 30);
    inventoryService.addProduct(product1);
    inventoryService.addProduct(product2);

    // Adding cashier
    Cashier cashier = new Cashier(1, "John Doe", 1500);
    store.addCashier(cashier);

    // Selling products
    try {
      salesService.sellProducts(cashier, Arrays.asList(product1, product2));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
