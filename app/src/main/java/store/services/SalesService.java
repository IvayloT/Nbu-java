package store.services;

import store.models.Cashier;
import store.models.Product;
import store.models.Receipt;
import store.models.Store;

import java.util.List;

public class SalesService {
  private Store store;

  public SalesService(Store store) {
    this.store = store;
  }

  public void sellProducts(Cashier cashier, List<Product> products) throws Exception {
    for (Product product : products) {
      if (product.isExpired()) {
        throw new Exception("Cannot sell expired product: " + product.getName());
      }
      store.addReceipt(new Receipt(cashier, products));
    }
  }

  // Other sales related methods
}
