package org.alfrendo.challenge1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static int nasiGorengOrderCount = 0;

    private static int mieGorengOrderCount = 0;

    private static int nasiAyamOrderCount = 0;

    private static int esTehManisOrderCount = 0;
    
    private static int esJerukOrderCount = 0;

    public static void main(String[] args) {
        runBinarFud();
    }

    private static void runBinarFud() {
        while (true) {
            int option = showMainMenu();
            showOrder(option);
        }
    }

    private static int showMainMenu() {
        System.out.println("==========================");
        System.out.println("Selamat datang di BinarFud");
        System.out.println("==========================\n");

        System.out.println("Silahkan pilih makanan :");
        System.out.println("1. Nasi Goreng\t| 15.000");
        System.out.println("2. Mie Goreng\t| 13.000");
        System.out.println("3. Nasi + Ayam\t| 18.000");
        System.out.println("4. Es Teh Manis\t| 3.000");
        System.out.println("5. Es Jeruk\t| 5.000");
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar aplikasi\n");

        System.out.print("=> ");

        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
            return 9999;
        }
    }

    private static void showOrder(int option) {
        if (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 99 && option != 0) {
            System.out.println("Tolong masukkan opsi yang valid\n");
            return;
        }

        if (option == 99) {
            while (true) {
                int confirmationOption = showConfirmationAndPayment();

                if (confirmationOption == 1) {
                    if (getTotalOrderCount() == 0) {
                        System.out.println("Pembayaran ditolak, anda belum memesan apapun");
                    } else {
                        // lanjut ke pembayaran dan cetak struk pembayaran
                        String text = getReceiptOfPayment();
                        createReceiptOfPayment(text);
                        resetState();
                        System.out.println("Pembayaran berhasil dilakukan dan struk berhasil dicetak\n");
                        return;
                    }
                } else if (confirmationOption == 2) {
                    System.out.println();
                    return;
                } else if (confirmationOption == 0) {
                    System.out.println("Aplikasi BinarFud Ditutup");
                    scanner.close();
                    System.exit(0);
                } else {
                    System.out.println("Silahkan masukkan opsi yang tersedia");
                }
            }
        } else if (option == 0) {
            System.out.println("Aplikasi BinarFud Ditutup");
            scanner.close();
            System.exit(0);
        } else {
            System.out.println("\n==========================");
            System.out.println("Berapa pesanan anda");
            System.out.println("==========================\n");

            if (option == 1) {
                while (true) {
                    System.out.println("Nasi Goreng\t\t| 15.000");
                    System.out.println("(input 0 untuk kembali)\n");

                    System.out.print("qty => ");

                    try {
                        nasiGorengOrderCount = nasiGorengOrderCount + scanner.nextInt();
                        System.out.println();
                        break;
                    } catch (InputMismatchException e) {
                        scanner.next();
                        System.out.println("Tolong masukkan inputan yang valid\n");
                    }
                }
            } else if (option == 2) {
                while (true) {
                    System.out.println("Mie Goreng\t\t| 13.000");
                    System.out.println("(input 0 untuk kembali)\n");

                    System.out.print("qty => ");

                    try {
                        mieGorengOrderCount = mieGorengOrderCount + scanner.nextInt();
                        System.out.println();
                        break;
                    } catch (InputMismatchException e) {
                        scanner.next();
                        System.out.println("Tolong masukkan inputan yang valid\n");
                    }
                }
            } else if (option == 3) {
                while (true) {
                    System.out.println("Nasi + Ayam\t\t| 18.000");
                    System.out.println("(input 0 untuk kembali)\n");

                    System.out.print("qty => ");

                    try {
                        nasiAyamOrderCount = nasiAyamOrderCount + scanner.nextInt();
                        System.out.println();
                        break;
                    } catch (InputMismatchException e) {
                        scanner.next();
                        System.out.println("Tolong masukkan inputan yang valid\n");
                    }
                }
            } else if (option == 4) {
                while (true) {
                    System.out.println("Es Teh Manis\t\t| 3.000");
                    System.out.println("(input 0 untuk kembali)\n");

                    System.out.print("qty => ");

                    try {
                        esTehManisOrderCount = esTehManisOrderCount + scanner.nextInt();
                        System.out.println();
                        break;
                    } catch (InputMismatchException e) {
                        scanner.next();
                        System.out.println("Tolong masukkan inputan yang valid\n");
                    }
                }
            } else if (option == 5) {
                while (true) {
                    System.out.println("Es Jeruk\t\t| 5.000");
                    System.out.println("(input 0 untuk kembali)\n");

                    System.out.print("qty => ");

                    try {
                        esJerukOrderCount = esJerukOrderCount + scanner.nextInt();
                        System.out.println();
                        break;
                    } catch (InputMismatchException e) {
                        scanner.next();
                        System.out.println("Tolong masukkan inputan yang valid\n");
                    }
                }
            }
        }

    }

    private static int showConfirmationAndPayment() {
        System.out.println("\n==========================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("==========================\n");

        if (nasiGorengOrderCount != 0) {
            System.out.println("Nasi Goreng\t\t" + nasiGorengOrderCount + "\t" + nasiGorengOrderCount * 15_000);
        }

        if (mieGorengOrderCount != 0) {
            System.out.println("Mie Goreng\t\t" + mieGorengOrderCount + "\t" + mieGorengOrderCount * 13_000);
        }

        if (nasiAyamOrderCount != 0) {
            System.out.println("Nasi Goreng\t\t" + nasiAyamOrderCount + "\t" + nasiAyamOrderCount * 18_000);
        }

        if (esTehManisOrderCount != 0) {
            System.out.println("Es Teh Manis\t\t" + esTehManisOrderCount + "\t" + esTehManisOrderCount * 3_000);
        }

        if (esJerukOrderCount != 0) {
            System.out.println("Es Jeruk\t\t" + esJerukOrderCount + "\t" + esJerukOrderCount * 5_000);
        }

        System.out.println("-------------------------------+");
        System.out.println("Total\t\t" + getTotalOrderCount() + "\t" + getTotalOrderPayment());
        
        System.out.println("\n1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi\n");

        System.out.print("=> ");

        try {
            int option = scanner.nextInt();
            if (option != 1 && option != 2 && option != 0) {
                return 9999;
            }
            return option;
        } catch (InputMismatchException e) {
            scanner.next();
            return 9999;
        }
    }

    private static int getTotalOrderCount() {
        return nasiGorengOrderCount
                + mieGorengOrderCount
                + nasiAyamOrderCount
                + esTehManisOrderCount
                + esJerukOrderCount;
    }

    private static int getTotalOrderPayment() {
        return (nasiGorengOrderCount * 15_000)
                + (mieGorengOrderCount * 13_000)
                + (nasiAyamOrderCount * 18_000)
                + (esTehManisOrderCount * 3_000)
                + (esJerukOrderCount * 5_000);
    }

    private static void resetState() {
        nasiGorengOrderCount = 0;
        mieGorengOrderCount = 0;
        nasiAyamOrderCount = 0;
        esTehManisOrderCount = 0;
        esJerukOrderCount = 0;
    }

    private static String getReceiptOfPayment() {
        return (
            "==========================\n" +
            "BinarFud\n" +
            "==========================\n\n" +
            "Di bawah ini adalah pesanan anda\n\n" +
            (nasiGorengOrderCount != 0 ? "Nasi Goreng\t\t " + nasiGorengOrderCount + "\t\t " + (nasiGorengOrderCount * 15_000) + "\n" : "") + 
            (mieGorengOrderCount != 0 ? "Mie Goreng\t\t " + mieGorengOrderCount + "\t\t " + (mieGorengOrderCount * 13_000) + "\n" : "") +
            (nasiAyamOrderCount != 0 ? "Nasi + Ayam\t\t " + nasiAyamOrderCount + "\t\t " + (nasiAyamOrderCount * 18_000) + "\n" : "") +
            (esTehManisOrderCount != 0 ? "Es Teh Manis\t\t " + esTehManisOrderCount + "\t\t " + (esTehManisOrderCount * 3_000) + "\n" : "") +
            (esJerukOrderCount != 0 ? "Es Jeruk\t\t " + esJerukOrderCount + "\t\t " + (esJerukOrderCount * 5_000) + "\n" : "") +
            "-------------------------------+\n" + 
            "Total\t\t\t " + getTotalOrderCount() + "\t\t " + getTotalOrderPayment() + "\n\n" +
            "Pembayaran : BinarCash\n\n" + 
            "==========================\n" + 
            "Simpan struk ini sebagai\n" + 
            "bukti pembayaran\n" + 
            "=========================="
        );
    }

    private static void createReceiptOfPayment(String text) {
        String rootPath = System.getProperty("user.dir");
        String directoryPath = rootPath + "/src/main/java/org/alfrendo/challenge1/struk.txt";
        try {
            FileWriter fileWriter = new FileWriter(directoryPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan " + e.getMessage());
        }
    }

}