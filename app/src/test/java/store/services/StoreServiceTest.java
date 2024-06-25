package store.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import store.exceptions.ExistingCashierForTheGivenCashierDeskException;
import store.exceptions.InsufficientFundsException;
import store.models.Cashier;
import store.models.CashierDesk;
import store.models.Product;
import store.models.Product.Category;
import store.models.Store;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

public class StoreServiceTest {

  private Store store;
  private StoreService storeService;
  private CashierDesk cashierDesk;
  private Cashier cashier;

  @BeforeEach
  void init() {
    store = new Store("Magazin1", 20, 10, 3, 3);
    storeService = new StoreService(store);
    cashierDesk = new CashierDesk(1);
    cashier = new Cashier(1, "John", 1000, cashierDesk.number);
  }

  @Test
  public void addCashierFailsTest() throws ExistingCashierForTheGivenCashierDeskException {
    Cashier cashier2 = new Cashier(2, "Johnn", 1000, cashierDesk.number);
    storeService.addCashier(cashier);
    assertThrows(ExistingCashierForTheGivenCashierDeskException.class, () -> storeService.addCashier(cashier2));
  }

  @Test
  public void addCashierSuccessTest() throws ExistingCashierForTheGivenCashierDeskException {
    storeService.addCashier(cashier);
    assertEquals(store.getCashiers().size(), 1);
  }

  @Test
  public void addProductTest() {
    Product product = new Product(1, "Bag", 1.0, Category.NON_FOOD, LocalDate.now().plusDays(7), 100);
    storeService.addProduct(product);
    assertEquals(store.getProducts().size(), 1);
  }

  @Test
  public void calculateSellingPriceTest() {
    Product productNonFood = new Product(1, "Bag", 1.0, Category.NON_FOOD, LocalDate.now().plusDays(7), 100);
    Product productFood = new Product(1, "Milk", 2.0, Category.FOOD, LocalDate.now().plusDays(7), 100);
    storeService.addProduct(productNonFood);
    storeService.addProduct(productFood);
    assertEquals(2.40, productFood.getSellingPrice());
    assertEquals(1.10, productNonFood.getSellingPrice());
  }

  @Test
  public void calculateTotalAmountTest() {
    assertEquals(4, storeService.calculateTotalAmount(2, 2));
  }

  @Test
  public void sellProductsTest() throws Exception {
    Product product = new Product(1, "Bag", 1.0, Category.NON_FOOD, LocalDate.now().plusDays(7), 100);
    storeService.addProduct(product);
    product.updateQuantity(50);
    storeService.sellProducts(cashier, store.getProducts(), 300);
    assertEquals(50, product.getAvailability());
    assertThrows(InsufficientFundsException.class, () -> storeService.sellProducts(cashier, store.getProducts(), 30));
  }
}
