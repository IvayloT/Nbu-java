package store.services;

import java.util.List;

import store.exceptions.ExistingCashierForTheGivenCashierDeskException;
import store.exceptions.ExpiredException;
import store.exceptions.InsufficientFundsException;
import store.exceptions.InsufficientProductQuantityException;
import store.models.Cashier;
import store.models.Product;
import store.models.Receipt;
import store.models.Store;

public class StoreService {

  private Store store;

  public StoreService(Store store) {
    this.store = store;
  }

  public void addCashier(Cashier cashier) throws ExistingCashierForTheGivenCashierDeskException {

    for (Cashier existingCashier : store.getCashiers()) {
      if (existingCashier.getCashDeskID() == cashier.getCashDeskID()) {
        throw new ExistingCashierForTheGivenCashierDeskException(existingCashier.getName(),
            existingCashier.getCashDeskID());
      }
    }

    store.addCashier(cashier);
  }

  public void sellProducts(Cashier cashier, List<Product> products, double customerMoney)
      throws Exception {
    double totalAmount = 0;
    for (Product product : products) {
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

    store.addReceipts(new Receipt(cashier, products, totalAmount));
  }

  public double getTotalRevenue() {
    return store.getReceipts().stream().mapToDouble(receipt -> receipt.getTotalAmount()).sum();
  }

  public double getTotalExpenses() {
    double salaries = store.getCashiers().stream().mapToDouble(cashier -> cashier.getSalary()).sum();
    double purchaseCosts = store.getProducts().stream().mapToDouble(product -> product.getDeliveryPrice()).sum();
    return salaries + purchaseCosts;
  }

  public double getProfit() {
    return getTotalRevenue() - getTotalExpenses();
  }

  public int getReceiptCounter() {
    return store.getReceipts().size();
  }

}
