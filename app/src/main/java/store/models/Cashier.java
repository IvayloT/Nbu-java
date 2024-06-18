package store.models;

import java.util.List;

public class Cashier {
  private int id;
  private String name;
  private double monthlySalary;
  private int cashDeskId;

  public Cashier(int id, String name, double monthlySalary, int cashDeskId) {
    this.id = id;
    this.name = name;
    this.monthlySalary = monthlySalary;
    this.cashDeskId = cashDeskId;
  }

  public double getSalary() {
    return monthlySalary;
  }

  public String getName() {
    return name;
  }

  public int getCashDeskID() {
    return cashDeskId;
  }

}
