package store.models;

import java.util.ArrayList;
import java.util.List;

import store.services.ProductService;

public class Store {
  public String name;
  public List<ProductService> products;
  public List<Cashier> cashiers;
  public List<Receipt> receipts;
  public double markupPercentageFood;
  public double markupPercentageNonFood;
  public double discountPercentage;
  public int discountDays;

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
}
