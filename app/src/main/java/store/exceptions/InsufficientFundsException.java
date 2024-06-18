package store.exceptions;

public class InsufficientFundsException extends Exception {

  public String toString() {
    return "InsufficientFundsException{" +
        "Insufficient funds to complete the purchase." +
        "} " + super.toString();
  }
}
