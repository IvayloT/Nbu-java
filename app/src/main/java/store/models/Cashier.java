package store.models;

public class Cashier {
  private int id;
  public String name;
  public double monthlySalary;
  public int cashDeskId;

  public Cashier(int id, String name, double monthlySalary, int cashDeskId) {
    this.id = id;
    this.name = name;
    this.monthlySalary = monthlySalary;
    this.cashDeskId = cashDeskId;
  }
}
