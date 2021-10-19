package DDP2.Lab04;

import java.util.Scanner;

public class Lab04 {
  public static int size;

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Ukuran kolom dan baris: ");
    size = Integer.parseInt(input.nextLine());
    int[][] peta = new int[size][size];
    
    System.out.println("Peta matrix: ");
    /* TO DO: Assign data ke multidimensional array */

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        peta[i][j] = input.nextInt();
      }
    }
    input.nextLine();

    System.out.print("Putaran: ");
    String urutanTahap = input.nextLine();

    for (int i = 0; i < urutanTahap.length(); i++) {
      char currentStep = urutanTahap.charAt(i);
      if (currentStep == 'L') {
        peta = putarPetaKiri(peta);
      } else if (currentStep == 'R') {
        peta = putarPetaKanan(peta);
      }
    }

    printPeta(peta, size);
    input.close();
  }

  public static void printPeta(int[][] peta, int size) {
    System.out.println("Output: ");
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        System.out.print(peta[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static int[][] putarPetaKiri(int[][] peta) {
    /* TO DO: Melakukan rotate kiri pada peta */

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {

        // Copy the array to a temp variable
        int[][] temp = new int[size][size];
        for (int t = 0; t < size; t++) {
          for (int u = 0; u < size; u++) {
            temp[t][u] = peta[t][u];
          }
        }

        // temp[u] transpose the matrix
        // temp[u] mirror the transpose so the matrix looks like the array
        // moved
        for (int t = 0; t < size; t++) {
          for (int u = 0; u < size; u++) {
            peta[t][u] = temp[u][size - 1 - t];
          }
        }
      }
    }
    return peta;
  }

  public static int[][] putarPetaKanan(int[][] peta) {
    /* TO DO: Melakukan rotate kanan pada peta */

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        int[][] temp = new int[size][size];
        for (int t = 0; t < size; t++) {
          for (int u = 0; u < size; u++) {
            temp[t][u] = peta[t][u];
          }
        }

        for (int t = 0; t < size; t++) {
          for (int u = 0; u < size; u++) {
            peta[u][size - 1 - t] = temp[t][u];
          }
        }
      }
    }
    return peta;
  }
}
