
import store.exceptions.ExistingCashierForTheGivenCashierDeskException;
import store.models.*;
import store.models.Product.Category;
import store.services.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Store store = new Store("Supermarket", 5, 2, 3, 3);
    StoreService storeService = new StoreService(store);

    // Adding products
    Product milk = new Product(1, "Milk", 1.5, Category.FOOD, LocalDate.now().plusDays(7), 100);
    Product soap = new Product(2, "Soap", 1, Category.NON_FOOD, LocalDate.now().plusMonths(3), 100);
    Product redTomato = new Product(3, "Red Tomato", 0.5, Category.FOOD, LocalDate.now().plusDays(5), 100);
    Product water = new Product(4, "Gorna Bankya", 0.8, Category.NON_FOOD, LocalDate.now().plusMonths(3), 100);
    Product pinkTomato = new Product(5, "Pink Tomato", 0.7, Category.FOOD, LocalDate.now().plusDays(5), 100);
    Product orangeJuice = new Product(6, "Orange Juice", 1.8, Category.NON_FOOD, LocalDate.now().plusMonths(3), 100);

    storeService.addProduct(milk);
    storeService.addProduct(soap);
    storeService.addProduct(redTomato);
    storeService.addProduct(orangeJuice);
    storeService.addProduct(water);
    storeService.addProduct(pinkTomato);

    // Adding cashier desk

    CashierDesk cashierDesk1 = new CashierDesk(1);
    CashierDesk cashierDesk2 = new CashierDesk(2);

    // Adding cashier
    Cashier cashier1 = new Cashier(1, "Georgi", 1200, cashierDesk1.number);
    Cashier cashier2 = new Cashier(2, "Mitko", 1000, cashierDesk2.number);

    try {
      storeService.addCashier(cashier1);
      storeService.addCashier(cashier2);
    } catch (ExistingCashierForTheGivenCashierDeskException e) {
      System.out.println(e.getMessage());
    }

    HashMap<String, Double> listCustomerProducts = new HashMap<String, Double>();
    listCustomerProducts.put("Milk", 2.0);
    listCustomerProducts.put("Soap", 2.0);
    listCustomerProducts.put("Red Tomato", 4.0);

    HashMap<String, Double> listCustomerProducts2 = new HashMap<String, Double>();
    listCustomerProducts2.put("Gorna Bankya", 15.0);
    listCustomerProducts2.put("Orange Juice", 2.0);

    List<Product> customerProducts = storeService.addToBasket(listCustomerProducts);
    List<Product> customerProducts2 = storeService.addToBasket(listCustomerProducts2);

    // Selling products
    try {
      storeService.sellProducts(cashier1, customerProducts, 30);
      storeService.sellProducts(cashier2, customerProducts2, 22);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    System.out.println("All available products: " + storeService.getAllProductsAvailable());
    System.out.println("All sold products: " + storeService.getAllSoldProducts());
    System.out.println("All hired cashiers: " + storeService.getCashierNames());
    System.out.println("Receipt counter: " + storeService.getReceiptCounter());
    System.out.println("Total expenses: " + storeService.getTotalExpenses());
    System.out.println("Total revenue: " + storeService.getTotalRevenue());
  }
}
