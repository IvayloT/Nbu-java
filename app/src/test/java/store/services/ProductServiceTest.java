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

  @BeforeEach
  void init() {
    store = new Store("Magazin1", 20, 10, 3, 3);
  }

  @Test
  public void calculateSellingPriceTest() {
    Product productFood = new Product(1, "Milk", 2.0, Category.FOOD, LocalDate.now().plusDays(7), 50, 100,
        store.markupPercentageFood, store.discountPercentage, store.discountDays);
    Product productNonFood = new Product(1, "Bag", 1.0, Category.NON_FOOD, LocalDate.now().plusDays(7), 50, 100,
        store.markupPercentageNonFood, store.discountPercentage, store.discountDays);
    ProductService foodProductService = new ProductService(productFood);
    ProductService nonFoodProductService = new ProductService(productNonFood);
    assertEquals(2.40, foodProductService.getSellingPrice());
    assertEquals(1.10, nonFoodProductService.getSellingPrice());
  }

  @Test
  public void sExpiredTest() {
    Product product = new Product(1, "Milk", 1.0, Category.FOOD, LocalDate.now().minusDays(1), 50, 100,
        store.markupPercentageFood, store.discountPercentage, store.discountDays);
    ProductService productService = new ProductService(product);
    assertTrue(productService.isExpired());
  }

  @Test
  public void getNameTest() {
    Product product = new Product(1, "Milk", 1.0, Category.FOOD, LocalDate.now().minusDays(1), 50, 100,
        store.markupPercentageFood, store.discountPercentage, store.discountDays);
    ProductService productService = new ProductService(product);
    assertEquals("Milk", productService.getName());
  }

  @Test
  public void availabilityTest() {
    Product product = new Product(1, "Milk", 1.0, Category.FOOD, LocalDate.now().minusDays(1), 50, 100,
        store.markupPercentageFood, store.discountPercentage, store.discountDays);
    ProductService productService = new ProductService(product);
    assertEquals(100, productService.getAvailability());
  }

}
