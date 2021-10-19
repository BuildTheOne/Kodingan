import java.util.*;

public class GuessNumber {
  public static void main(String[] args) {
    try (Scanner input = new Scanner(System.in)) {
      while (true) {
        boolean play = true;
        while (play) {
          System.out.print("Choose limit number : ");
          int bound = input.nextInt();

          System.out.print("Choose times : ");
          int times = input.nextInt();

          Random random = new Random();
          int answer = random.nextInt(bound);

          for (int i = 0; i < times; i++) {
            System.out.print("Guess the Number : ");
            int answerInput = input.nextInt();
            if (answerInput < answer) {
              System.out.println("Too little");
              continue;
            } else if (answerInput > answer) {
              System.out.println("Too much");
              continue;
            } else {
              System.out.println("Correct");
              break;
            }
          }
        }
      }
    }
  }
}
