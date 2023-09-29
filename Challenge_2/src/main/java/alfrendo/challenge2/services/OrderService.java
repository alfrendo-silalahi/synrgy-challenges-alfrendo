package alfrendo.challenge2.services;

import alfrendo.challenge2.databases.Database;
import alfrendo.challenge2.models.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OrderService {

    private final Database db = new Database();

    public List<Order> getOrders() {
        return db.getOrders();
    }

    public Order getOrderByIndex(int option) {
        return db.getOrderByIndex(option - 1);
    }

    public List<Order> getSelectedOrder() {
        var orders = db.getOrders();
        return orders.stream().filter(order -> order.getQuantity() != 0).toList();
    }

    public int getOrderQuantities() {
        var orders = db.getOrders();
        var orderQuantities = 0;
        for (var order : orders) {
            if (order.getQuantity() != 0) {
                orderQuantities += order.getQuantity();
            }
        }
        return orderQuantities;
    }

    public int getOrderPaymentTotal() {
        var orders = db.getOrders();
        var orderPaymentTotal = 0;
        for (var order : orders) {
            if (order.getQuantity() != 0) {
                orderPaymentTotal += order.getQuantity() * order.getPrice();
            }
        }
        return orderPaymentTotal;
    }

    public void processPaymentAndReceiptOfPayment() {
        createReceiptOfPayment();
    }

    private void createReceiptOfPayment() {
        String rootPath = System.getProperty("user.dir");
        String directoryPath = rootPath + "/src/main/java/alfrendo/challenge2/struk.txt";

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(directoryPath))) {

            bufferedWriter.write("==========================\n");
            bufferedWriter.write("BinarFud\n");
            bufferedWriter.write("==========================\n\n");

            bufferedWriter.write("Di bawah ini adalah pesanan anda\n\n");

            var selectedOrders = getSelectedOrder();
            for (var order : selectedOrders) {
                bufferedWriter.write(order.getName() + " " + order.getQuantity() + " " + (order.getQuantity() * order.getPrice()));
                bufferedWriter.newLine();
            }

            bufferedWriter.write("-------------------------------+\n");
            bufferedWriter.write("Total " + getOrderQuantities() + " " + getOrderPaymentTotal());
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            bufferedWriter.write("Pembayaran : BinarCash\n\n");

            bufferedWriter.write("==========================\n");
            bufferedWriter.write("Simpan struk ini sebagai\n");
            bufferedWriter.write("bukti pembayaran\n");
            bufferedWriter.write("==========================");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan " + e.getMessage());
        }

        resetStateOrders();
    }

    private void resetStateOrders() {
        var orders = db.getOrders();

        for (var order : orders) {
            order.setQuantity(0);
        }
    }


}
