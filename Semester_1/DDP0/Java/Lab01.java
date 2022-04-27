package DDP0.KMK;

import java.util.Scanner;

public class Lab01 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan tanggal : ");
        int day = input.nextInt();

        System.out.print("Masukkan bulan : ");
        int month = input.nextInt();

        System.out.print("Masukkan tahun : ");
        int year = input.nextInt();
        input.nextLine();

        System.out.print("Masukkan ucapan : ");
        String message = input.nextLine();

        System.out.println(message);
        boolean date_is_correct = false;
        boolean message_is_correct = false;

        if (day % 2 == 0 && month % 2 == 0 && year % 2 == 0) {
            date_is_correct = true;
        }
        if (message.equals("good doggo")) {
            message_is_correct = true;
        }

        if (message_is_correct && date_is_correct) {
            System.out.println("Cheo the good doggo wishes you good luck");
        } else if (message_is_correct) {
            System.out.println("The message is correct but the date is not");
        } else if (date_is_correct) {
            System.out.println("The date is correct but the message is not");
        } else {
            System.out.println("Cheo the good doggo cannot give you luck and fortune");
        }

        input.close();
    }
}
