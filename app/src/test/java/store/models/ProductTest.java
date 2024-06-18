package store.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import store.models.Product.Category;

public class ProductTest {

  private Product productFood;

  @BeforeEach
  void init() {
    productFood = new Product(1, "Milk", 2.0, Category.FOOD, LocalDate.now().plusDays(7), 100);
  }

  @Test
  public void sExpiredTest() {
    Product product = new Product(1, "Milk", 1.0, Category.FOOD, LocalDate.now().minusDays(1), 100);
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
