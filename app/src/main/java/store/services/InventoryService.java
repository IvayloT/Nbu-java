package store.services;

import store.models.Product;
import store.models.Store;

public class InventoryService {
  private Store store;

  public InventoryService(Store store) {
    this.store = store;
  }

  public void addProduct(Product product) {
    store.addProduct(product);
  }

  // Other inventory related methods
}
