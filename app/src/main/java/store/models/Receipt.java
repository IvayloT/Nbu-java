package store.models;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

import java.io.FileWriter;
import java.io.IOException;

public class Receipt {
  private int receiptNumber;
  private Cashier cashier;
  private LocalDateTime dateTime;
  private List<Product> products;
  private double totalAmount;

  public Receipt(int receiptNumber, Cashier cashier, List<Product> products, double totalAmount) {
    this.receiptNumber = receiptNumber;
    this.cashier = cashier;
    this.dateTime = LocalDateTime.now();
    this.products = products;
    this.totalAmount = totalAmount;
    saveToFile();
  }

  private void saveToFile() {
    try (FileWriter writer = new FileWriter("receipts/receipt_" + receiptNumber + ".txt")) {
      writer.write(this.toString());
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String toString() {
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    StringBuilder sb = new StringBuilder();
    sb.append("Receipt Number: ").append(receiptNumber).append("\n")
        .append("Cashier: ").append(cashier.getName()).append("\n")
        .append("CashierDesk: ").append(cashier.getCashDeskID()).append("\n")
        .append("Date/Time: ").append(dateTime.format(myFormatObj)).append("\n")
        .append("Products:\n");

    for (Product product : products) {
      sb.append(product.getName()).append(" - ")
          .append(product.getSellingPrice()).append(" x ")
          .append(product.getQuantity()).append("\n");
    }

    sb.append("Total Amount: ").append(Math.round(totalAmount * 100.0) / 100.0);
    return sb.toString();
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public List<Product> getProducts() {
    return products;
  }

}
