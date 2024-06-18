package store.models;

import java.util.ArrayList;
import java.util.List;

public class Store {
  private String name;
  private List<Product> products;
  private List<Cashier> cashiers;
  private List<Receipt> receipts;
  private double markupPercentageFood;
  private double markupPercentageNonFood;
  private double discountPercentage;
  private int discountDays;

  public Store(String name, double markupPercentageFood, double markupPercentageNonFood, double discountPercentage,
      int discountDays) {
    this.name = name;
    this.products = new ArrayList<>();
    this.cashiers = new ArrayList<>();
    this.receipts = new ArrayList<>();
    this.markupPercentageFood = markupPercentageFood;
    this.markupPercentageNonFood = markupPercentageNonFood;
    this.discountPercentage = discountPercentage;
    this.discountDays = discountDays;
  }

  public void addProduct(Product product) {
    products.add(product);
  }

  public void addCashier(Cashier cashier) {
    cashiers.add(cashier);
  }

  public void addReceipts(Receipt receipt) {
    receipts.add(receipt);
  }

  public List<Product> getProducts() {
    return products;
  }

  public List<Cashier> getCashiers() {
    return cashiers;
  }

  public List<Receipt> getReceipts() {
    return receipts;
  }

  public double getMarkupPercentageFood() {
    return markupPercentageFood;
  }

  public double getMarkupPercentageNonFood() {
    return markupPercentageNonFood;
  }

  public double getDiscountPercentage() {
    return discountPercentage;
  }

  public int getDiscountDays() {
    return discountDays;
  }
}
