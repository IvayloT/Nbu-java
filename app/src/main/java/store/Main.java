package store;

import store.exceptions.ExistingCashierForTheGivenCashierDeskException;
import store.models.*;
import store.models.Product.Category;
import store.services.*;

import java.time.LocalDate;
import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    Store store = new Store("Supermarket", 5, 2, 3, 3);
    StoreService storeService = new StoreService(store);

    // Adding products
    Product milk = new Product(1, "Milk", 1.5, Category.FOOD, LocalDate.now().plusDays(7), 100);
    Product soap = new Product(2, "Soap", 1, Category.NON_FOOD, LocalDate.now().plusMonths(3), 100);
    Product tomato = new Product(3, "Tomato", 0.5, Category.FOOD, LocalDate.now().plusDays(5), 100);

    store.addProduct(milk);
    store.addProduct(soap);
    store.addProduct(tomato);

    // Adding cashier desk

    CashierDesk cashierDesk1 = new CashierDesk(1);
    CashierDesk cashierDesk2 = new CashierDesk(2);

    // Adding cashier
    Cashier cashier1 = new Cashier(1, "Georgi", 1200, cashierDesk1.number);
    Cashier cashier2 = new Cashier(2, "Mitko", 1200, cashierDesk2.number);

    HashMap<String, Double> basketProducts = new HashMap<String, Double>();
    basketProducts.put("Milk", 2.0);
    basketProducts.put("Soap", 2.0);
    basketProducts.put("Tomato", 1.0);
    basketProducts.put("Milk", 2.0);

    try {
      storeService.addCashier(cashier1);
      storeService.addCashier(cashier2);
    } catch (ExistingCashierForTheGivenCashierDeskException e) {
      System.out.println(e.getMessage());
    }

    // Selling products
    try {
      storeService.sellProducts(cashier1, store.getProducts(), 100);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
