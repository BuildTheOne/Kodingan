package DDP2.Lab08;

// Buat BoredException dengan meng-extend Exception
// Buat constructor yang memiliki parameter message
public class BoredException extends Exception{

  public BoredException(String message) {
    super(message);
  }
}
