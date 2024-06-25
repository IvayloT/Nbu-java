package store.models;

import java.time.LocalDate;

public class Product {
  private int idNumber;
  private String name;
  private double deliveryPrice;
  private double sellingPrice;
  private Category category;
  private LocalDate expiryDate;
  private double quantity;
  private double availability;

  public Product(int idNumber, String name, double deliveryPrice, Category category, LocalDate expiryDate,
      double availability) {
    this.idNumber = idNumber;
    this.name = name;
    this.deliveryPrice = deliveryPrice;
    this.category = category;
    this.expiryDate = expiryDate;
    this.availability = availability;
    this.quantity = 0.0;
  }

  public enum Category {
    FOOD,
    NON_FOOD
  }

  public String getName() {
    return name;
  }

  public double getSellingPrice() {
    return Math.round(sellingPrice * 100.0) / 100.0;
  }

  public double getQuantity() {
    return quantity;
  }

  public double getAvailability() {
    return availability;
  }

  public boolean isExpired() {
    return LocalDate.now().isAfter(expiryDate);
  }

  public double getDeliveryPrice() {
    return deliveryPrice;
  }

  public LocalDate getExpiryDate() {
    return expiryDate;
  }

  public Category getCategory() {
    return category;
  }

  public void updateSellingPrice(double selingPrice) {
    this.sellingPrice = selingPrice;
  }

  public void updateQuantity(double quantity) {
    this.quantity = quantity;
  }

  public void updateAvailability(double availability) {
    this.availability = availability;
  }

}
