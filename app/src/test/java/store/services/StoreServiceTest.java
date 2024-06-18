package store.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import store.exceptions.ExistingCashierForTheGivenCashierDeskException;
import store.models.Cashier;
import store.models.CashierDesk;
import store.models.Product;
import store.models.Product.Category;
import store.models.Store;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class StoreServiceTest {

  private Store store;
  private StoreService storeService;

  @BeforeEach
  void init() {
    store = new Store("Magazin1", 5, 2, 3, 3);
    storeService = new StoreService(store);
  }

  @Test
  public void addProductTest() {
    Product product = new Product(1, "Milk", 1.0, Category.FOOD, LocalDate.now().minusDays(1), 50, 100,
        store.getMarkupPercentageFood(), store.getDiscountPercentage(), store.getDiscountDays());
    store.addProduct(product);
    assertEquals(1, store.getProducts().size());
  }

  @Test
  public void addCashierFailsTest() throws ExistingCashierForTheGivenCashierDeskException {
    CashierDesk cashierDesk = new CashierDesk(1);
    Cashier cashier1 = new Cashier(1, "John", 1000, cashierDesk.number);
    Cashier cashier2 = new Cashier(2, "Johnn", 1000, cashierDesk.number);
    storeService.addCashier(cashier1);
    assertThrows(ExistingCashierForTheGivenCashierDeskException.class, () -> storeService.addCashier(cashier2));
  }

  @Test
  public void addCashierSuccessTest() throws ExistingCashierForTheGivenCashierDeskException {
    CashierDesk cashierDesk = new CashierDesk(1);
    Cashier cashier = new Cashier(1, "John", 1000, cashierDesk.number);
    storeService.addCashier(cashier);
    assertEquals(store.getCashiers().size(), 1);
  }
}
