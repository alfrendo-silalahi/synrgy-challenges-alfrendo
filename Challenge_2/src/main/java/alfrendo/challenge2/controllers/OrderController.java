package alfrendo.challenge2.controllers;

import alfrendo.challenge2.models.Order;
import alfrendo.challenge2.services.OrderService;

import java.util.List;

public class OrderController {

    private final OrderService orderService = new OrderService();

    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    public Order getOrderByIndex(int option) {
        try {
            if (option <= 0) {
                System.out.println("Masukkan opsi pesanan yang tersedia\n");
                return null;
            }
            return orderService.getOrderByIndex(option);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Masukkan opsi pesanan yang tersedia\n");
            return null;
        }
    }

    public List<Order> getSelectedOrder() {
        return orderService.getSelectedOrder();
    }

    public int getOrderQuantities() {
        return orderService.getOrderQuantities();
    }

    public int getOrderPaymentTotal() {
        return orderService.getOrderPaymentTotal();
    }

    public void processPaymentAndReceiptOfPayment() {
        orderService.processPaymentAndReceiptOfPayment();
    }

}
