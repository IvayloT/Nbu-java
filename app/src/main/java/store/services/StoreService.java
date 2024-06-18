package store.services;

import java.util.List;

import store.exceptions.ExistingCashierForTheGivenCashierDeskException;
import store.exceptions.ExpiredException;
import store.exceptions.InsufficientFundsException;
import store.exceptions.InsufficientProductQuantityException;
import store.models.Cashier;
import store.models.Receipt;
import store.models.Store;

public class StoreService {

  private Store store;

  public StoreService(Store store) {
    this.store = store;
  }

  public void addProduct(ProductService product) {
    store.products.add(product);
  }

  public List<ProductService> getProducts() {
    return store.products;
  }

  public void addCashier(Cashier cashier) throws ExistingCashierForTheGivenCashierDeskException {

    for (Cashier existingCashier : store.cashiers) {
      if (existingCashier.cashDeskId == cashier.cashDeskId) {
        throw new ExistingCashierForTheGivenCashierDeskException(existingCashier.name, existingCashier.cashDeskId);
      }
    }

    store.cashiers.add(cashier);
  }

  public void sellProducts(Cashier cashier, List<ProductService> products, double customerMoney)
      throws Exception {
    double totalAmount = 0;
    for (ProductService product : products) {
      if (product.isExpired()) {
        throw new ExpiredException(product.getName());
      } else if (product.getQuantity() > product.getAvailability()) {
        throw new InsufficientProductQuantityException();
      }
      totalAmount += product.getSellingPrice();
    }

    if (customerMoney < totalAmount) {
      throw new InsufficientFundsException();
    }

    store.receipts.add(new Receipt(cashier, products, totalAmount));
  }

  public double getTotalRevenue() {
    return store.receipts.stream().mapToDouble(receipt -> receipt.totalAmount).sum();
  }

  public double getTotalExpenses() {
    double salaries = store.cashiers.stream().mapToDouble(cashier -> cashier.monthlySalary).sum();
    double purchaseCosts = store.products.stream().mapToDouble(product -> product.getDeliveryPrice()).sum();
    return salaries + purchaseCosts;
  }

  public double getProfit() {
    return getTotalRevenue() - getTotalExpenses();
  }

  public int getReceiptCounter() {
    return store.receipts.size();
  }

}
