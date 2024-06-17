package store.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

  @Test
  public void testCalculateSellingPrice() {
    Product product = new Product(1, "Milk", 1.0, "food", LocalDate.now().plusDays(7), 50);
    assertEquals(1.2, product.getSellingPrice(), 0.01);
  }

  @Test
  public void testIsExpired() {
    Product product = new Product(1, "Milk", 1.0, "food", LocalDate.now().minusDays(1), 50);
    assertTrue(product.isExpired());
  }
}
