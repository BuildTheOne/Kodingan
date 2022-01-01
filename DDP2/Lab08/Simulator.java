package DDP2.Lab08;

import java.util.Scanner;

public class Simulator {
  public static void main(String[] args) throws FullException {
    Scanner input = new Scanner(System.in);
    Dogegochi dogegochi = new Dogegochi();
    boolean looper = true;
    String commandString;

    System.out.println("Selamat Datang di Dogegochi!");
    System.out.println("Perintah yang dapat dijalankan Dogegochi :");
    System.out.println("1. Main");
    System.out.println("2. Makan");
    System.out.println("3. Selesai Bermain");

    // What's changed : Mengubah isi while loop menjadi try-catch format
    while (looper) {
      try {
        System.out.println();
        dogegochi.getStatus();
        System.out.print("Masukkan Perintah: ");
        commandString = input.nextLine();
        int command = Integer.parseInt(commandString);
        if (command == 1) {
          dogegochi.play();
        } else if (command == 2) {
          System.out.print("Masukkan Makanan dan jumlahnya: ");
          String food = input.nextLine();
          String[] foodCommand = food.split(" ");
          int quantity = Integer.parseInt(foodCommand[1]);
          dogegochi.eat(foodCommand[0], quantity);
        } else if (command == 3) {
          looper = false;
        } else {
          System.out.println("[FAILED: Doge tidak mengerti perintah ini]");
        }
        // Menambah catch exception handler, jenis exception diurutkan sesuai ketentuan
        // di docs
        // Ditambakan continue agar dapat melanjutkan while loop
        // (Kedua built-in exception diimplementasi disini karena error-nya
        // terjadi di file ini, bukan di method di dogegochi)
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("[FAILED: Kamu tidak memberikan jumlah makanan yang ingin diberikan]");
        continue;
      } catch (NumberFormatException e) {
        System.out.println("[FAILED: Jumlah Makanan harus berupa angka]");
        continue;
      } catch (BoredException e) {
        System.out.println(e.getMessage());
        continue;
      } catch (FullException e) {
        System.out.println(e.getMessage());
        continue;
      } catch (HungerException e) {
        System.out.println(e.getMessage());
        continue;
      }
    }
    System.out.println("[Bye-bye doge!!]");
    input.close();
  }
}
