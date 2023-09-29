package alfrendo.challenge2.views;

import alfrendo.challenge2.constants.Constant;
import alfrendo.challenge2.controllers.OrderController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Page {

    private final OrderController orderController = new OrderController();

    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean exit = false;
        while (!exit) {
            int optionAfterHomePage = showHomePage();

            if (optionAfterHomePage == 99) {
                int optionAfterConfirmationAndPaymentPage = showConfirmationAndPaymentPage();
                if (optionAfterConfirmationAndPaymentPage == 1) {
                    orderController.processPaymentAndReceiptOfPayment();
                } else if (optionAfterConfirmationAndPaymentPage == 2) {
                    // kembali ke menu utama
                } else if (optionAfterConfirmationAndPaymentPage == 0) {
                    System.out.println("Aplikasi BinarFud ditutup!");
                    scanner.close();
                    System.exit(0);
                }
            } else if (optionAfterHomePage == 0) {
                System.out.println("Aplikasi BinarFud ditutup!");
                scanner.close();
                exit = true;
            } else {
                showQuantityPage(optionAfterHomePage);
            }
        }
    }

    private int showHomePage() {
        System.out.println(Constant.LINE_TWO);
        System.out.println("Selamat datang di BinarFud");
        System.out.println(Constant.LINE_TWO + "\n");

        System.out.println("Silahkan pilih makanan:");

        var orders = orderController.getOrders();
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". " + orders.get(i).getName() + " | " + orders.get(i).getPrice());
        }
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar aplikasi\n");

        System.out.print("=> ");

        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Input yang diperbolehkan adalah bilangan bulat (int)\n");
            scanner.next();
            return -1;
        }
    }

    private void showQuantityPage(int option) {
        if (option == -1) {
            return;
        }

        var order = orderController.getOrderByIndex(option);

        if (order == null) {
            return;
        }

        int quantity;

        while (true) {
            System.out.println("==========================");
            System.out.println("Berapa pesanan anda");
            System.out.println("==========================\n");

            System.out.println(order.getName() + " | " + order.getPrice());
            System.out.println("input 0 untuk kembali\n");

            System.out.print("qty => ");

            try {
                quantity = scanner.nextInt();
                if (quantity < 0) {
                    System.out.println("Quantity tidak boleh lebih kecil dari 0!\n");
                } else {
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Input yang diperbolehkan adalah bilangan bulat (int)!\n");
                scanner.next();
            }
        }

        order.setQuantity(order.getQuantity() + quantity);
        System.out.println();
    }

    private int showConfirmationAndPaymentPage() {
        System.out.println("==========================");
        System.out.println("Konfirmasi dan Pembayaran");
        System.out.println("==========================\n");

        var selectedOrders = orderController.getSelectedOrder();
        selectedOrders.forEach(order -> System.out.println(order.getName() + " " + order.getQuantity() + " " + (order.getQuantity() * order.getPrice())));
        System.out.println("--------------------------+");
        System.out.println("Total " + orderController.getOrderQuantities() + " " + orderController.getOrderPaymentTotal());

        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar Applikasi\n");

        System.out.print("=> ");
        return scanner.nextInt();
    }

}
