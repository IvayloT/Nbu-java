package store.exceptions;

public class ExpiredException extends Exception {

  private String productName;

  public ExpiredException(String productName) {
    this.productName = productName;
  }

  public String toString() {
    return "ExpiredException{" +
        "Product " + productName + " is expired and cannot be sold." +
        "} " + super.toString();
  }
}
