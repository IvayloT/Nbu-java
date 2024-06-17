package store.models;

import java.time.LocalDateTime;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class Receipt {
  private static int receiptCounter = 0;
  private int receiptNumber;
  private Cashier cashier;
  private LocalDateTime dateTime;
  private List<Product> products;
  private double totalAmount;

  public Receipt(Cashier cashier, List<Product> products, double totalAmount) {
    this.receiptNumber = ++receiptCounter;
    this.cashier = cashier;
    this.dateTime = LocalDateTime.now();
    this.products = products;
    this.totalAmount = totalAmount;
    saveToFile();
  }

  private void saveToFile() {
    try (FileWriter writer = new FileWriter("receipt_" + receiptNumber + ".txt")) {
      writer.write(this.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Receipt Number: ").append(receiptNumber).append("\n")
        .append("Cashier: ").append(cashier.getName()).append("\n")
        .append("Date/Time: ").append(dateTime).append("\n")
        .append("Products:\n");

    for (Product product : products) {
      sb.append(product.getName()).append(" - ")
          .append(product.getSalePrice()).append(" x ")
          .append(product.getQuantity()).append("\n");
    }

    sb.append("Total Amount: ").append(totalAmount);
    return sb.toString();
  }

  public static int getReceiptCounter() {
    return receiptCounter;
  }
}
