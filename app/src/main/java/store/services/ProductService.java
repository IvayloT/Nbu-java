package store.services;

import store.models.Product;

import java.time.LocalDate;

public class ProductService {
  private Product product;

  public ProductService(Product product) {
    this.product = product;
  }

  public String getName() {
    return product.name;
  }

  public double getSellingPrice() {
    return product.sellingPrice;
  }

  public double getQuantity() {
    return product.quantity;
  }

  public double getAvailability() {
    return product.availability;
  }

  public boolean isExpired() {
    return LocalDate.now().isAfter(product.expiryDate);
  }

  public double getDeliveryPrice() {
    return product.deliveryPrice;
  }

}