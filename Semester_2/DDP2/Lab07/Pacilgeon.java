package DDP2.Lab07;

import java.util.Scanner;
import java.util.Random;

public class Pacilgeon {

    /*
     * SIMULASI PACILGEON Catatan: Pintu masuk ada di (row, col) = (0, 0). Jika N
     * adalah ukuran row, dan M adalah ukuran col, maka pintu keluar ada di (row,
     * col) = (N - 1, M - 1). Ukuran Pacilgeon lebih dari 1 satuan luas. Asumsikan
     * di pintu masuk tidak ada monster. Dalam input map, selain ‘G’ dan ‘K’ akan
     * tidak dianggap.
     */

    private static Monster[][] monsterMap;
    private static char[][] charMap;
    private static Player player;
    private static Scanner input;
    private static Random random;
    private static int rowSize;
    private static int colSize;
    private static int rowNow;
    private static int colNow;

    /*
     * menerima input map yang menunjukkan letak Kobold dan Goblin di dalam
     * Pacilgeon
     */
    private static void getMap() {
        System.out.print("Row size: ");
        rowSize = input.nextInt();

        System.out.print("Column size: ");
        colSize = input.nextInt();

        monsterMap = new Monster[rowSize][colSize];

        System.out.println("Input map:");
        System.out.println("K: Kobold, G: Goblin");

        char ch;
        charMap = new char[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            String s = input.next();
            for (int j = 0; j < colSize; j++) {
                ch = s.charAt(j);
                charMap[i][j] = ch;
                switch (ch) {
                    case 'K':
                        monsterMap[i][j] = new Kobold(random.nextInt(100) + 1, random.nextInt(21));
                        break;
                    case 'G':
                        monsterMap[i][j] = new Goblin(random.nextInt(100) + 1, random.nextInt(16));
                        break;
                }
            }
        }
    }

    /*
     * mengeluarkan peta dari Pacilgeon
     */
    private static void printMap() {
        System.out.println("Map: (K: Kobold, G: Goblin, o: player is here)");
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (rowNow == i && colNow == j) {
                    System.out.print("o");
                } else if (monsterMap[i][j] == null) {
                    System.out.print(".");
                } else {
                    System.out.print(charMap[i][j]);
                }
            }
            System.out.println();
        }
    }

    /*
     * melakukan simulasi pertarungan antara player dengan monster
     */
    private static boolean battle() {
        if (monsterMap[rowNow][colNow] != null) {
            Monster monster = monsterMap[rowNow][colNow];
            System.out.println();
            System.out.println("Battle with a monster!");
            System.out.println(monster.toString());
            System.out.println();

            boolean playerTurn = true;
            while (player.getIsAlive() && monster.getIsAlive()) {
                if (playerTurn) {
                    player.attack(monster);
                } else {
                    monster.attack(player);
                }
                playerTurn = !playerTurn;
            }
            if (!player.getIsAlive()) {
                System.out.println();
                System.out.println("==============================================");
                System.out.println();
                System.out.println("You have been defeated. Try again next time.");
                System.exit(1);
            } else {
                monsterMap[rowNow][colNow] = null;
                monster.dropItem(player);
            }
            System.out.println();
            return true;
        }
        return false;
    }

    /*
     * mengecek apakah posisi player sudah ada di pintu keluar
     */
    private static boolean finish() {
        return (rowNow == rowSize - 1) && (colNow == colSize - 1);
    }

    /*
     * mengecek apakah gerakan yang dibuat player membuat player masih ada di dalam
     * index yang valid (ukuran Pacilgeon)
     */
    private static boolean isValidMove(int nextRow, int nextCol) {
        return (0 <= nextRow) && (nextRow < rowSize) && (0 <= nextCol) && (nextCol < colSize);
    }

    /*
     * melakukan gerakan sesuai input
     */
    private static void move() {
        while (true) {
            printMap();
            System.out.println("Current position | row: " + rowNow + ", col: " + colNow);
            System.out.print("Move (W / A / S / D): ");
            int nextRow = rowNow;
            int nextCol = colNow;

            String s = input.next();
            char ch = s.charAt(0);
            switch (ch) {
                case 'W':
                    nextRow--;
                    break;
                case 'A':
                    nextCol--;
                    break;
                case 'S':
                    nextRow++;
                    break;
                case 'D':
                    nextCol++;
                    break;
            }
            if (isValidMove(nextRow, nextCol)) {
                rowNow = nextRow;
                colNow = nextCol;
                break;
            } else {
                System.out.println("Invalid move!");
                System.out.println();
            }
        }
    }

    /*
     * simulasi Pacilgeon
     */
    private static void play() {
        System.out.print("Player name: ");
        String namaPemain = input.next();
        System.out.println();
        player = new Player(namaPemain);

        while (player.getIsAlive() && !finish()) {
            System.out.println("Player stats:");
            System.out.println(player.toString());

            move();

            System.out.println("==============================================");
            boolean haveBattle = battle();
            if (!haveBattle) {
                System.out.println();
                System.out.println("Nothing to do here.");
                System.out.println();
            }

            System.out.println("==============================================");
            System.out.println();
        }

        System.out.println("Congratulations! You have finished the Pacilgeon :D");
    }

    public static void main(String[] args) {
        input = new Scanner(System.in);
        random = new Random();
        rowNow = 0;
        colNow = 0;

        getMap();
        play();

        input.close();
    }
}