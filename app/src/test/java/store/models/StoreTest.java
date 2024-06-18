package store.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import store.models.Product.Category;
import store.services.StoreService;

public class StoreTest {

  private Store store;
  private StoreService storeService;
  private Product product;

  @BeforeEach
  void init() {
    store = new Store("Magazin1", 5, 2, 3, 3);
    storeService = new StoreService(store);
    product = new Product(1, "Milk", 1.0, Category.FOOD, LocalDate.now().minusDays(1), 100);
  }

  @Test
  public void getProductsTest() {
    storeService.addProduct(product);
    assertEquals(1, store.getProducts().size());
  }

  @Test
  public void getMarkupPercentageFoodTest() {
    assertEquals(5, store.getMarkupPercentageFood());
  }

  @Test
  public void addProductTest() {
    store.addProduct(product);
    assertEquals(1, store.getProducts().size());
  }

}
