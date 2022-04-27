package DDP2.Tugas1;

import java.util.Scanner;

public class Tugas1 {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        int hour, minute, second;

        System.out.print("Masukkan jam   : ");
        hour = input.nextInt();
        System.out.print("Masukkan menit : ");
        minute = input.nextInt();
        System.out.print("Masukkan detik : ");
        second = input.nextInt();

        if (second == 59) {
            if (minute == 59) {
                if (hour == 23) {
                    hour = 0;
                } else {
                    hour ++;
                }
                minute = 0;
            } else {
                minute ++;
            }
            second = 0;
        } else {
            second++;
        }

        System.out.println("Waktu satu detik kemudian adalah : " + hour + "-" + minute + "-" + second);

        input.close();
    }
}