package DDP2.Lab01;

import java.util.Scanner;

public class Lab01b {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Masukkan jumlah pizza yang ingin dibeli : ");
    int pizzaCount = input.nextInt();

    int pizzaPrice = 30000;
    int discountCount = 0;

    for (int i = 1; i < pizzaCount + 1; i++) {
      discountCount += i;
      if (discountCount >= 70) {
        discountCount = 70;
        break;
      }
    }

    int discountCut = ((pizzaPrice * pizzaCount) * discountCount) / 100;
    int priceDiscount = (pizzaPrice * pizzaCount) - discountCut;

    System.out.println("Anda mendapatkan diskon " + discountCount + "%");
    System.out.println("Yang harus kamu bayar sebesar: Rp" + priceDiscount);

    switch (discountCount % 7) {
      case 0:
        System.out.println("Cola satu liter gratis untuk kamu");
        break;
      case 2:
        System.out.println("Lemon tea dua gelas gratis untuk kamu");
        break;
      case 4:
        System.out.println("Es teh manis gratis untuk kamu");
        break;
      case 5:
        System.out.println("Teh tawar hangat gratis untuk kamu");
        break;
      case 6:
        System.out.println("Air matang gratis untuk kamu");
        break;
      default:
        System.out.println("Maaf kamu terpaksa seret");
        break;
    }

    input.close();
  }
}
