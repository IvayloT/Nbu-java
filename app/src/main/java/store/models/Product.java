package store.models;

import java.time.LocalDate;

public class Product {
  private int idNumber;
  private String name;
  private double deliveryPrice;
  private double sellingPrice;
  private Category category;
  private LocalDate expiryDate;
  private int quantity;

  public Product(int idNumber, String name, double deliveryPrice, Category category, LocalDate expiryDate,
      int quantity) {
    this.idNumber = idNumber;
    this.name = name;
    this.deliveryPrice = deliveryPrice;
    this.category = category;
    this.expiryDate = expiryDate;
    this.quantity = quantity;
    this.sellingPrice = calculateSellingPrice();
  }

  public enum Category {
    FOOD,
    NON_FOOD
  }

  // Getters and Setters

  public double getSalePrice(double markupPercentage, double discountPercentage, int discountDays) {
    double salePrice = this.purchasePrice * (1 + markupPercentage / 100);
    if (expiryDate.isBefore(LocalDate.now().plusDays(discountDays))) {
      salePrice *= (1 - discountPercentage / 100);
    }
    return salePrice;
  }

  public boolean isExpired() {
    return LocalDate.now().isAfter(expiryDate);
  }
}
