package store.models;

import java.util.ArrayList;
import java.util.List;

public class Store {
  private List<Product> products;
  private List<Cashier> cashiers;
  private List<Receipt> receipts;
  private double percentageFood;
  private double percentageNonFood;
  private double discountPercentage;
  private int discountDays;

  public Store(double percentageFood, double percentageNonFood, double discountPercentage,
      int discountDays) {
    this.products = new ArrayList<>();
    this.cashiers = new ArrayList<>();
    this.receipts = new ArrayList<>();
    this.percentageFood = percentageFood;
    this.percentageNonFood = percentageNonFood;
    this.discountPercentage = discountPercentage;
    this.discountDays = discountDays;
  }

  public void addProduct(Product product) {
    products.add(product);
  }

  public void addCashier(Cashier cashier) {
    cashiers.add(cashier);
  }

  public void sellProducts(Cashier cashier, List<Product> products, double customerMoney) throws Exception {
    double totalAmount = 0;
    for (Product product : products) {
      if (product.isExpired()) {
        throw new Exception("Product " + product.getName() + " is expired and cannot be sold.");
      }
      totalAmount += product.getSalePrice();
    }

    if (customerMoney < totalAmount) {
      throw new Exception("Insufficient funds to complete the purchase.");
    }

    receipts.add(new Receipt(cashier, products, totalAmount));
  }

  public double getTotalRevenue() {
    return receipts.stream().mapToDouble(Receipt::getTotalAmount).sum();
  }

  public double getTotalExpenses() {
    double salaries = cashiers.stream().mapToDouble(Cashier::getMonthlySalary).sum();
    double purchaseCosts = products.stream().mapToDouble(Product::getPurchasePrice).sum();
    return salaries + purchaseCosts;
  }

  public double getProfit() {
    return getTotalRevenue() - getTotalExpenses();
  }
}
