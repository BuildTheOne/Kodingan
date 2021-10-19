package DDP2.Lab01;

import java.util.Scanner;

public class Lab01a {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan tanggal : ");
        int day = input.nextInt();
        input.nextLine();

        System.out.print("Masukkan bulan : ");
        String month = input.nextLine();

        System.out.print("Masukkan ucapan : ");
        String message = input.nextLine();

        boolean correctMessage = false;
        boolean correctDate = false;

        if (day == 10 && month.equals("juli")) {
            correctDate = true;
        }
        if (message.equals("happy birthday")) {
            correctMessage = true;
        }

        if (correctDate && correctMessage) {
            System.out.println("Terima kasih");
        } else {
            if (correctMessage) {
                System.out.println("Terima kasih tapi ini bukan tanggal lahir aku :D");
            } else if (day == 10 && !month.equals("juli")) {
                System.out.println("Tanggalnya benar, bulannya salah");
            } else if (month.equals("juli") && day != 10) {
                System.out.println("Bulannya benar, tanggalnya salah");
            } else {
                System.out.println("Biasalah :v");
            }
        }

        input.close();
    }
}
