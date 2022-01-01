package DDP0.KMK;

import java.util.Scanner;

public class Lab02 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                System.out.print("Masukkan Nama Planet : ");
                String planetName = input.nextLine();
                System.out.print("Masukkan Jumlah Alien : ");
                int alienCount = input.nextInt();

                System.out.println("Nama planet yang ditemukan : " + planet(planetName.toLowerCase()));
                System.out.println("Jumlah alien yang ditemukan : " + alien(alienCount));

                input.nextLine();
            }
        }
    }

    private static String planet(String planetName) {
        if (planetName.length() <= 1) {
            return planetName;
        } else {
            if (planetName.charAt(0) == planetName.charAt(1)) {
                return planet(planetName.substring(1));
            } else {
                return planetName.charAt(0) + planet(planetName.substring(1));
            }
        }
    }

    private static int alien(int alienCount) {
        if (alienCount < 10) {
            return alienCount;
        } else {
            return alien(alienCount % 10 + alien(alienCount / 10));
        }
    }
}
