package DDP2.Tugas1;

import java.util.Scanner;

public class BonusTugas1 {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        int hour, minute, second;

        System.out.print("Masukkan jam   : ");
        hour = input.nextInt();
        System.out.print("Masukkan menit : ");
        minute = input.nextInt();
        System.out.print("Masukkan detik : ");
        second = input.nextInt();

        if (checkValidInput(second, minute, hour)) {
            if (second == 59) {
                if (minute == 59) {
                    if (hour == 23) {
                        hour = 0;
                    } else {
                        hour++;
                    }
                    minute = 0;
                } else {
                    minute++;
                }
                second = 0;
            } else {
                second++;
            }
            System.out.println("Waktu satu detik kemudian adalah : " + hour + "-" + minute + "-" + second);
        } else {
            messageValidInput(second, minute, hour);
        }

        input.close();
    }

    private static Boolean checkValidInput(int second, int minute, int hour) {
        if (second > 59 || second < 0 || minute > 59 || minute < 0 || hour > 23 || hour < 0) {
            return false;
        }
        return true;
    }

    private static void messageValidInput(int second, int minute, int hour) {
        if (hour > 23 || hour < 0) {
            System.out.println("Maaf, nilai jam yang diperbolehkan hanya berkisar antara 0-23");
        }
        if (minute > 59 || minute < 0) {
            System.out.println("Maaf, nilai menit yang diperbolehkan hanya berkisar antara 0-59");
        }
        if (second > 59 || second < 0) {
            System.out.println("Maaf, nilai detik yang diperbolehkan hanya berkisar antara 0-59");
        }
    }
}
