package DDP2.Lab02;

import java.util.Scanner; // Util for take input from user

public class Lab02 {
  public static void main(String[] args) {
    // Initialize Scanner for take input from user
    Scanner input = new Scanner(System.in);

    // Array is used to split the message and the encrypt-decrypt command, which is splitted by a space
    String[] inputUser = input.nextLine().split(" ");
    // Define the message and the command from the array
    String msg = inputUser[0];
    String cmd = inputUser[1];

    // Count the D and E using countChar method
    int countD = countChar(cmd, 'D');
    int countE = countChar(cmd, 'E');
    // For loop is used to encrypt and/or decrypt the message
    // Flow : 
    // 1. Initialize encrypt and/or decrypt method to a temp string
    // 2. Put the temp variavle to msg variable, so it would loop the result of encrypt and/or decrypt method
    // 3. Print the final result
    for (int i = 0; i < countE; i++) {
      String temp = encrypt(msg);
      msg = temp;
    }
    for (int i = 0; i < countD; i++) {
      String temp = decrypt(msg);
      msg = temp;
    }
    System.out.println(msg);

    input.close();
  }

  public static String encrypt(String msg) {
    /* Method for encrypt the message */
    // Initialize variable for return output
    String encode = "";
    // For loop for add 3 to each char so it could be encrypted
    for (int i = 0; i < msg.length(); i++) {
      char x = (char) (msg.charAt(i) + 3); // change the char
      // Conditional below is for concluded if the char above is bigger than char Z or
      // not
      // If yes, subtract 26 - 3 to change it back to char A
      // Else, add 3
      if (x > 'Z') {
        encode += (char) ((msg.charAt(i) - (26 - 3)));
      } else {
        encode += (char) (msg.charAt(i) + 3);
      }
    }
    return encode;
  }

  public static String decrypt(String msg) {
    /* Method for decrypt the message */
    // Initialize variable for return output
    String decode = "";
    // For loop for subtract 3 to each char so it could be decrypted
    for (int i = 0; i < msg.length(); i++) {
      char x = (char) (msg.charAt(i) - 3); // change the char
      // Conditional below is for concluded if the char above is smaller char A or not
      // If yes, add 26 - 3 to change it back to char Z
      // Else, subtract 3
      if (x < 'A') {
        decode += (char) ((msg.charAt(i) + (26 - 3)));
      } else {
        decode += (char) (msg.charAt(i) - 3);
      }
    }
    return decode;
  }

  public static int countChar(String cmd, char charInp) {
    /* Method for count the D and E occurrence using for loop */
    int count = 0;
    for (int i = 0; i < cmd.length(); i++) {
      if (cmd.charAt(i) == charInp) {
        count++;
      }
    }
    return count;
  }
}
