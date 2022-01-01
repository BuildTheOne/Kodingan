package DDP2.Lab03;

import java.util.*;

public class Lab03 {
  static int N;

  public static int countChar(String cmd, char charInp) {
    /* Method for count the L and R occurrence using for loop */
    // Note: This method is reused from my Lab 2 assignment
    int count = 0;
    for (int i = 0; i < cmd.length(); i++) {
      if (cmd.charAt(i) == charInp) {
        count++;
      }
    }
    return count;
  }

  public static int findMinimum(int[] arr) {
    // Method to find minimum value of array using for loop
    // The idea is to assign the value of first index to a variable,
    // Then check if the other elements is lower than it
    // If an element is lower, assign the element into said variable
    int minValue = arr[0];
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < minValue) {
        minValue = arr[i];
      }
    }
    return minValue;
  }

  public static boolean checkMinimum(int[] arr) {
    // Method to check if the first index of the array is the minimum value
    // If the arr is empty, return false
    if (arr.length == 0) {
      return false;
    }
    // Use the findMinimum method above to get the minimum value
    int minValue = findMinimum(arr);
    // Check if the first index of arr is equal to minValue
    // If it's equal, return true, else false
    if (minValue == arr[0]) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Panjang: ");
    N = Integer.parseInt(input.nextLine());
    int[] arr = new int[N];

    System.out.print("Barisan: ");
    for (int i = 0; i < N; i++) {
      int data = input.nextInt();
      arr[i] = data;
    }
    input.nextLine();

    System.out.print("Pergeseran: ");
    String geser = input.nextLine();

    // Count the L and R command using countChar method
    int countL = countChar(geser, 'L');
    int countR = countChar(geser, 'R');

    int[] newArr = new int[arr.length]; // Initialize a new empty array to put the shifted elements
    int[] temp = arr;

    // Shift the elements to left and right using for loop respectively
    for (int l = 0; l < countL; l++) {
      // Using for loop to shift the elements value one by one
      for (int i = 0; i < arr.length; i++) {
        if (i == arr.length - 1) { // If i is the last element, change the last value into the first value
          newArr[i] = temp[0];
        } else { // Else, change the i-th value into the next value
          newArr[i] = temp[i + 1];
        }
      }
      // For loop below is used to copy element of newArr to temp so it wouldn't mix
      // up the temp because in Java, if two arrays is connected with "=", it's not
      // copying elements but referencing to same array.
      // Hence, if a element in one array is changed, the element in other array is
      // also changed
      for (int a = 0; a < newArr.length; a++) {
        temp[a] = newArr[a];
      }
    }
    // Similar with the L command
    for (int r = 0; r < countR; r++) {
      for (int j = 0; j < arr.length; j++) {
        if (j == 0) { // If the element is the first value, change the value into the last value
          newArr[j] = temp[arr.length - 1];
        } else { // Else, change the i-th value into the previous value
          newArr[j] = temp[j - 1];
        }
      }
      for (int a = 0; a < newArr.length; a++) {
        temp[a] = newArr[a];
      }
    }

    System.out.println(Arrays.toString(newArr)); // Print the shifted array
    // Use checkMinimum method to check if the first index value is equal to minimum
    // value
    if (checkMinimum(arr)) {
      System.out.println(checkMinimum(arr));
    } else {
      System.out.println(checkMinimum(arr));
    }

    input.close();
  }
}
// Note: the comments has the weird cut because I'm using prettier in
// vscode and I'm too lazy to put the comments in the good way
