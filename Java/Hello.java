import java.util.Scanner;

public class Hello {
    public static void main(String[] args){
        System.out.println("Hello, World!");
        Scanner input = new Scanner(System.in);

        String userInput = input.nextLine();

        System.out.println("Halo, " + userInput);
        input.close();
    }
}
