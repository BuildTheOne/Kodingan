package SDA.Lab5;

import java.io.*;
import java.util.*;

public class Lab5 {
  private static InputReader in = new InputReader(System.in);
  private static PrintWriter out = new PrintWriter(System.out);

  static class Candy {
    String name;
    long price;
    long type;
    Candy right;
    Candy left;
    long height;

    public Candy(String name, long price, long type) {
      this.name = name;
      this.price = price;
      this.type = type;
      right = null;
      left = null;
      height = 0;
    }

    public String printProperty() {
      return ("Name: " + name + "\nPrice:" + price + "\nType: " + type);
    }

    public String toString() {
      return name;
    }
  }

  static class Indimaret {
    Candy root;

    public Indimaret() {
      root = null;
    }

    public Long getHeight(Candy candy) {
      if (candy == null) return 0L;
      return candy.height;
    }

    public Candy rightRotation(Candy candy) {
      Candy x = candy.left;
      Candy y = x.right;

      x.right = candy;
      candy.left = y;

      candy.height = Math.max(getHeight(candy.left), getHeight(candy.right)) + 1;
      x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

      return x;
    }

    public Candy leftRotation(Candy candy) {
      Candy x = candy.right;
      Candy y = x.left;

      x.left = candy;
      candy.right = y;
      
      candy.height = Math.max(getHeight(candy.left), getHeight(candy.right)) + 1;
      x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

      return x;
    }

    public Long getBalance(Candy candy) {
      if (candy == null) return 0L;
      return getHeight(candy.left) - getHeight(candy.right);
    }

    public void addStock(String name, long price, long type) {
      if (root == null) {
        root = insert(root, name, price, type);
      } else {
        insert(root, name, price, type);
      }
      preOrder(root);
    }

    public Candy insert(Candy root, String name, long price, long type) {
      if (root == null) {
        return (new Candy(name, price, type));
      } else {
        if (price < root.price) {
          root.left = insert(root.left, name, price, type);
        } else if (price > root.price) {
          root.right = insert(root.right, name, price, type);
        } else {
          return root;
        }
      }
      

      root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

      long balance = getBalance(root);

      if (balance > 1 && price < root.left.price)
            return rightRotation(root);
      if (balance < -1 && price > root.right.price)
          return leftRotation(root);
      if (balance > 1 && price > root.left.price) {
          root.left = leftRotation(root.left);
          return rightRotation(root);
      }
      if (balance < -1 && price < root.right.price) {
          root.right = rightRotation(root.right);
          return leftRotation(root);
      }

      return root;
    }

    void preOrder(Candy node) {
        if (node != null) {
            System.out.print(node.price + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
  }

  static Indimaret indimaret = new Indimaret();

  public static void main(String[] args) {

    // Menginisialisasi kotak sebanyak N
    int N = in.nextInt();
    for (int i = 0; i < N; i++) {
      String nama = in.next();
      int harga = in.nextInt();
      int tipe = in.nextInt();
      handleStock(nama, harga, tipe);
    }

    // Query
    // (method dan argumennya boleh diatur sendiri, sesuai kebutuhan)
    int NQ = in.nextInt();
    for (int i = 0; i < NQ; i++) {
      String Q = in.next();
      if (Q.equals("BELI")) {
        int L = in.nextInt();
        int R = in.nextInt();
        out.println(handleBeli(L, R));
      } else if (Q.equals("STOCK")) {
        String nama = in.next();
        int harga = in.nextInt();
        int tipe = in.nextInt();
        handleStock(nama, harga, tipe);
      } else { // SOLD_OUT
        String nama = in.next();
        handleSoldOut(nama);
      }
    }

    out.flush();
  }

  static void handleStock(String nama, long harga, long tipe) {
    indimaret.addStock(nama, harga, tipe);
  }

  static void handleSoldOut(String nama) {
  }

  static String handleBeli(int L, int R) {
    return "";
  }

  // taken from https://codeforces.com/submissions/Petr
  static class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
      reader = new BufferedReader(new InputStreamReader(stream), 32768);
      tokenizer = null;
    }

    public String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return tokenizer.nextToken();
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }
  }
}