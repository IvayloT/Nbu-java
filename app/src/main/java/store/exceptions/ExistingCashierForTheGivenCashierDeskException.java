package store.exceptions;

public class ExistingCashierForTheGivenCashierDeskException extends Exception {

  private String cashierName;
  private int cashDeskID;

  public ExistingCashierForTheGivenCashierDeskException(String cashierName, int cashDeskID) {
    this.cashierName = cashierName;
    this.cashDeskID = cashDeskID;
  }

  public String toString() {
    return "ExistingCashierForTheGivenCashierDeskException{" +
        "This cashierDesk " + cashDeskID + " is already taken by: " + cashierName + "." +
        "} " + super.toString();
  }

}
