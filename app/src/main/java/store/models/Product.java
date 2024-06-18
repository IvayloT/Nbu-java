package store.models;

import java.time.LocalDate;

public class Product {
  private int idNumber;
  public String name;
  public double deliveryPrice;
  public double sellingPrice;
  public Category category;
  public LocalDate expiryDate;
  public double quantity;
  public double availability;

  public Product(int idNumber, String name, double deliveryPrice, Category category, LocalDate expiryDate,
      int quantity, double availability, double markupPercentage, double discountPercentage, int discountDays) {
    this.idNumber = idNumber;
    this.name = name;
    this.deliveryPrice = deliveryPrice;
    this.category = category;
    this.expiryDate = expiryDate;
    this.quantity = quantity;
    this.availability = availability;
    this.sellingPrice = calculateSellingPrice(markupPercentage, discountPercentage, discountDays);
  }

  public enum Category {
    FOOD,
    NON_FOOD
  }

  public double calculateSellingPrice(double markupPercentage, double discountPercentage, int discountDays) {
    double salePrice = this.deliveryPrice + (this.deliveryPrice * (markupPercentage / 100));
    if (expiryDate.isBefore(LocalDate.now().plusDays(discountDays))) {
      salePrice = salePrice - (salePrice *= (discountPercentage / 100));
    }
    return salePrice;
  }
}
