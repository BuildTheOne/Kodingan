import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

// TODO - class untuk Lantai
class Lantai {
  String name;
  Lantai prev;
  Lantai next;

  public Lantai(String name) {
    this.name = name;
    this.prev = null;
    this.next = null;
  }

  public String getValue() {
    return this.name;
  }

  public String toString() {
    return this.name;
  }
}

// TODO - class untuk Gedung
class Gedung {
  String name;
  Lantai head;
  Lantai position;
  Lantai tail;

  public Gedung(String name) {
    this.name = name;
    this.head = null;
    this.tail = null;
    this.position = null;
  }

  public void bangun(String input) {
    // TODO - handle BANGUN
    Lantai l = new Lantai(input);
    if (position == null) {
      position = l;
      head = l;
    } else if (position.next == null) {
      position.next = l;
      l.prev = position;
      position = l;
    } else {
      l.next = position.next;
      l.prev = position;
      position.next.prev = l;
      position.next = l;
      position = l;
    }
    if (position.next == null) {
      tail = position;
    }
  }

  public String lift(String input) {
    // TODO - handle LIFT
    if (position == null)
      return "";
    if (input.equals("ATAS")) {
      if (position.next != null) {
        position = position.next;
      }
    } else {
      if (position.prev != null) {
        position = position.prev;
      }
    }
    return position.name;
  }

  public String hancurkan() {
    // TODO - handle HANCURKAN
    if (position == null)
      return "";
    Lantai l = position;

    if (position.prev == null && position.next == null) {
      position = null;
      head = null;
      tail = null;
      return l.name;
    }

    if (position.prev == null) {
      head = position.next;
      position.next.prev = null;
      position = position.next;
    } else if (position.next == null) {
      tail = position.prev;
      position.prev.next = null;
      position = position.prev;
    } else {
      position.prev.next = position.next;
      position.next.prev = position.prev;
      position = position.prev;
    }
    return l.name;
  }

  public void timpa(Gedung h) {
    // TODO - handle TIMPA
    if (h.head != null) {
      tail.next = h.head;
      h.head.prev = tail;
      h.position = null;
      tail = h.tail;
      if (position == tail) {
        position.next = h.head;
      }
      h.tail = null;
      h.head = null;
    }
  }

  public String sketsa() {
    // TODO - handle SKETSA
    Lantai l = head;
    StringBuilder s = new StringBuilder();
    while (l != null) {
      s.append(l.name);
      l = l.next;
    }
    return s.toString();
  }

}

public class Lab4 {
  private static InputReader in;
  public static PrintWriter out;
  public static Gedung Gedung;

  public static void main(String[] args) throws IOException {
    InputStream inputStream = System.in;
    in = new InputReader(inputStream);
    OutputStream outputStream = System.out;
    out = new PrintWriter(outputStream);

    HashMap<String, Gedung> listGedung = new HashMap<>();

    // N operations
    int N = in.nextInt();
    String cmd;

    // TODO - handle inputs
    for (int zz = 0; zz < N; zz++) {

      cmd = in.next();

      if (cmd.equals("FONDASI")) {
        String A = in.next();
        listGedung.put(A, new Gedung(A));

      } else if (cmd.equals("BANGUN")) {
        String A = in.next();
        String X = in.next();
        // TODO
        Gedung g = listGedung.get(A);
        g.bangun(X);

      } else if (cmd.equals("LIFT")) {
        String A = in.next();
        String X = in.next();
        // TODO
        Gedung g = listGedung.get(A);
        out.println(g.lift(X));

      } else if (cmd.equals("SKETSA")) {
        String A = in.next();
        // TODO
        Gedung g = listGedung.get(A);
        out.println(g.sketsa());

      } else if (cmd.equals("TIMPA")) {
        String A = in.next();
        String B = in.next();
        // TODO
        Gedung g = listGedung.get(A);
        Gedung h = listGedung.get(B);
        g.timpa(h);
        listGedung.remove(B);

      } else if (cmd.equals("HANCURKAN")) {
        String A = in.next();
        // TODO
        Gedung g = listGedung.get(A);
        out.println(g.hancurkan());
      }
    }

    // don't forget to close/flush the output
    out.close();
  }

  // taken from https://codeforces.com/submissions/Petr
  // together with PrintWriter, these input-output (IO) is much faster than the
  // usual Scanner(System.in) and System.out
  // please use these classes to avoid your fast algorithm gets Time Limit
  // Exceeded caused by slow input-output (IO)
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