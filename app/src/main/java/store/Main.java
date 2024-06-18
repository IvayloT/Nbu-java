package store;

import store.exceptions.ExistingCashierForTheGivenCashierDeskException;
import store.models.*;
import store.models.Product.Category;
import store.services.*;

import java.time.LocalDate;

public class Main {
  public static void main(String[] args) {
    Store store = new Store("Magazin1", 5, 2, 3, 3);
    StoreService storeService = new StoreService(store);

    // Adding products
    Product product1 = new Product(1, "Milk", 1.0, Category.FOOD, LocalDate.now().plusDays(7), 50, 100,

        store.markupPercentageFood, store.discountPercentage, store.discountDays);
    Product product2 = new Product(2, "Soap", 0.5, Category.NON_FOOD, LocalDate.now().plusMonths(3), 30, 100,
        store.markupPercentageNonFood, store.discountPercentage, store.discountDays);

    ProductService productServiceOne = new ProductService(product1);
    ProductService productServiceSecond = new ProductService(product2);

    storeService.addProduct(productServiceOne);
    storeService.addProduct(productServiceSecond);

    // Adding cashier desk

    CashierDesk cashierDesk1 = new CashierDesk(1);
    CashierDesk cashierDesk2 = new CashierDesk(2);

    // Adding cashier
    Cashier cashier1 = new Cashier(1, "Georgi", 1200, cashierDesk1.number);
    Cashier cashier2 = new Cashier(2, "Mitko", 1200, cashierDesk2.number);
    try {
      storeService.addCashier(cashier1);
      storeService.addCashier(cashier2);
    } catch (ExistingCashierForTheGivenCashierDeskException e) {
      System.out.println(e.getMessage());
    }

    // Selling products
    try {
      storeService.sellProducts(cashier1, storeService.getProducts(), 100);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
