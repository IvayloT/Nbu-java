package store.exceptions;

public class InsufficientProductQuantityException extends Exception {

  public String toString() {
    return "InsufficientProductQuantityException{" +
        "There is not enough quantity in the store. " +
        "} " + super.toString();
  }

}