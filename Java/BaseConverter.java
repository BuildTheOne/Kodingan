import java.util.Scanner;

public class BaseConverter {
  public static void main(String[] args) {
    System.out.println("Welcome To Base Converter");
    try (Scanner input = new Scanner(System.in)) {
      while (true) {
        System.out.println(
            "1. Decimal to Standard Bases\n2. Binary to Standard Bases\n3. Octal to Standard Bases\n4. Hex to Standard Bases\n5. Standard Base to Other Bases\n6. Other Base to Standard Bases\n7. Other Base to Other Base");
        System.out.print("Enter your command : ");
        int inputted = input.nextInt();

        System.out.print("Enter your input : ");
        String num = input.next();

        try {
          switch (inputted) {
            case 1:
              int numDec = Integer.parseInt(num);
              System.out.println("Decimal : " + num);
              System.out.println("Binary  : " + DecimalToOther(numDec, 2));
              System.out.println("Octal   : " + DecimalToOther(numDec, 8));
              System.out.println("Hex     : " + DecimalToOther(numDec, 16));
              break;
            case 2:
              System.out.println("Binary  : " + num);
              System.out.println("Decimal : " + OtherToDecimal(num, 2));
              System.out.println("Octal   : " + OtherToOther(num, 2, 8));
              System.out.println("Hex     : " + OtherToOther(num, 2, 16));
              break;
            case 3:
              System.out.println("Octal   : " + num);
              System.out.println("Decimal : " + OtherToDecimal(num, 8));
              System.out.println("Binary  : " + OtherToOther(num, 8, 2));
              System.out.println("Hex     : " + OtherToOther(num, 8, 16));
              break;
            case 4:
              System.out.println("Hex     : " + num);
              System.out.println("Decimal : " + OtherToDecimal(num, 16));
              System.out.println("Binary  : " + OtherToOther(num, 16, 2));
              System.out.println("Octal   : " + OtherToOther(num, 16, 8));
              break;
            default:
            System.out.println("Command error");
          }
        } catch (Exception numberFroException) {
          System.out.println("The input is not valid for this base");
        }
        System.out.println();
      }
    }
  }

  private static String DecimalToOther(int num, int base) {
    String result;
    switch (base) {
      case 2:
        result = Integer.toBinaryString(num);
        break;
      case 8:
        result = Integer.toOctalString(num);
        break;
      case 16:
        result = Integer.toHexString(num);
        break;
      default:
        result = "";
    }
    return result;
  }

  private static int OtherToDecimal(String num, int base) {
    int result = Integer.parseInt(num, base);
    return result;
  }

  private static String OtherToOther(String num, int base1, int base2) {
    int res1 = OtherToDecimal(num, base1);
    String res2 = DecimalToOther(res1, base2);
    return res2;
  }
}
