package SDA.Lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Lab6 {
  private static InputReader in;
  private static PrintWriter out;

  static List<Land> tanah = new ArrayList<>();
  static Land[] tanahHeap = new Land[400005];
  static int position = 0;

  static class Land {
    long height;
    int index;
    int pos;

    public Land(long height, int index, int pos) {
      this.height = height;
      this.index = index;
      this.pos = pos;
    }

    public String toString() {
      return Long.toString(height);
    }
  }

  static public void add(Land land) {
    tanah.add(land);
    tanahHeap[position++] = land;

    int pos = position - 1;

    while (tanahHeap[pos].height < tanahHeap[pos / 2].height) {
      swap(pos, pos / 2);
      pos = pos / 2;
    }
    // printHeap();
  }

  static public void swap(int root, int curr) {
    tanahHeap[root].pos = tanahHeap[curr].pos;
    tanahHeap[curr].pos = tanahHeap[root].pos;
    Land tmp = tanahHeap[root];
    tanahHeap[root] = tanahHeap[curr];
    tanahHeap[curr] = tmp;
  }

  static public void A(int y) {
    add(new Land(y, tanah.size(), position));
    // printHeap();
  }

  static public void U(int x, int y) {
    Land l = tanahHeap[x];
    l.height = y;
    // printHeap();
    if (tanahHeap[x * 2 + 1] == null && tanahHeap[x * 2 + 2] == null) {
    }
    // printHeap();
  }

  static public void R() {
    Land min = tanahHeap[0];
    // out.println(min.index);

    if (position == 1) {
      out.println(min.height + " " + 0);
    } else {
      if (min.index == 0) {
        Land next = tanah.get(1);
        long max = Math.max(min.height, next.height);
        min.height = next.height = max;
        out.println(max + " " + min.index);
      } else if (min.index == position - 1) {
        Land prev = tanah.get(position - 1);
        long max = Math.max(min.height, prev.height);
        min.height = prev.height = max;
        out.println(max + " " + min.index);
      } else {
        Land prev = tanah.get(min.index - 1);
        Land next = tanah.get(min.index + 1);
        long max = Math.max(prev.height, next.height);
        max = Math.max(max, min.height);
        min.height = prev.height = next.height = max;
        out.println(max + " " + min.index);
      }
    }
  }

  static public void printHeap() {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < position; i++) {
      Long l = tanahHeap[i].height;
      s.append(l + " ");
    }
    out.println(s.toString());
  }

  public static void main(String[] args) {
    InputStream inputStream = System.in;
    in = new InputReader(inputStream);
    OutputStream outputStream = System.out;
    out = new PrintWriter(outputStream);

    int N = in.nextInt();
    for (int i = 0; i < N; i++) {
      int height = in.nextInt();
      add(new Land(height, i, i));
    }

    int Q = in.nextInt();
    while (Q-- > 0) {
      String query = in.next();
      if (query.equals("A")) {
        // TODO: Handle query A
        int y = in.nextInt();
        A(y);
      } else if (query.equals("U")) {
        // TODO: Handle query U
        int x = in.nextInt();
        int y = in.nextInt();
        U(x, y);
      } else {
        // TODO: Handle query R
        R();
      }
    }

    out.flush();
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

    public long nextLong() {
      return Long.parseLong(next());
    }

  }
}