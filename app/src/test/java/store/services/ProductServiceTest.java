package store.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import store.models.Product;
import store.models.Product.Category;
import store.models.Store;

public class ProductServiceTest {

  private Store store;
  private Product productFood;

  @BeforeEach
  void init() {
    store = new Store("Magazin1", 20, 10, 3, 3);
    productFood = new Product(1, "Milk", 2.0, Category.FOOD, LocalDate.now().plusDays(7), 50, 100,
        store.getMarkupPercentageFood(), store.getDiscountPercentage(), store.getDiscountDays());
  }

  @Test
  public void calculateSellingPriceTest() {
    Product productNonFood = new Product(1, "Bag", 1.0, Category.NON_FOOD, LocalDate.now().plusDays(7), 50, 100,
        store.getMarkupPercentageNonFood(), store.getDiscountPercentage(), store.getDiscountDays());
    assertEquals(2.40, productFood.getSellingPrice());
    assertEquals(1.10, productNonFood.getSellingPrice());
  }

  @Test
  public void sExpiredTest() {
    Product product = new Product(1, "Milk", 1.0, Category.FOOD, LocalDate.now().minusDays(1), 50, 100,
        store.getMarkupPercentageFood(), store.getDiscountPercentage(), store.getDiscountDays());
    assertTrue(product.isExpired());
  }

  @Test
  public void getNameTest() {
    assertEquals("Milk", productFood.getName());
  }

  @Test
  public void availabilityTest() {
    assertEquals(100, productFood.getAvailability());
  }

}
