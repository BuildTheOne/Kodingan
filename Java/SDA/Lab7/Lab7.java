package SDA.Lab7;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Lab7 {
  private static InputReader in;
  private static PrintWriter out;

  static Map<Integer, City> listCity = new HashMap<>();

  static class City {
    List<Road> listRoad = new ArrayList<>();
    int name;

    public City(int name) {
      this.name = name;
    }

    public String toString() {
      return Integer.toString(name);
    }
  }

  static class Road {
    City city1;
    City city2;
    Integer tol;

    public Road(City city1, City city2, Integer tol) {
      this.city1 = city1;
      this.city2 = city2;
      this.tol = tol;
    }

    public String toString() {
      return ("Road from " + city1 + " to " + city2 + " price " + tol);
    }
  }

  // TODO: method to initialize graph
  public static void createGraph(int N) {
    for (int i = 1; i <= N; i++) {
      listCity.put(i, new City(i));
    }
  }

  // TODO: method to create an edge with type==T that connects vertex U and vertex
  // V in a graph
  public static void addEdge(int U, int V, int T) {
    Road road = new Road(listCity.get(U), listCity.get(V), T);
    listCity.get(U).listRoad.add(road);
    listCity.get(V).listRoad.add(road);
    // out.println(listCity.get(U).listRoad.toString());
    // out.println(listCity.get(V).listRoad.toString());
  }

  // TODO: Handle teman X Y K
  public static void canMudik(int X, int Y, int K) {
    Map<City, Integer> sumTiket = new HashMap<>();
    Queue<City> queue = new ArrayDeque<>();
    boolean visited[] = new boolean[listCity.size() + 1];
    queue.add(listCity.get(X));
    sumTiket.put(listCity.get(X), 0);

    while (!queue.isEmpty()) {
      City c = queue.peek();
      visited[c.name] = true;
      for (int i = 0; i < c.listRoad.size(); i++) {
        Road r = c.listRoad.get(i);
        City temp;
        if (c.equals(r.city1))
          temp = r.city2;
        else
          temp = r.city1;

        if (visited[temp.name]) {
          if (sumTiket.get(temp) > sumTiket.get(c) + r.tol) {
            sumTiket.put(temp, sumTiket.get(c) + r.tol);
            if (!queue.contains(temp))
              queue.add(temp);
          }
        } else {
          sumTiket.put(temp, sumTiket.get(c) + r.tol);
          queue.add(temp);
          visited[temp.name] = true;
        }
      }
      //out.println(queue.toString());
      queue.remove();
    }
    if (K >= sumTiket.get(listCity.get(Y))) {
      out.println(1);
    } else {
      out.println(0);
    }
  }

  public static void main(String[] args) {
    InputStream inputStream = System.in;
    in = new InputReader(inputStream);
    OutputStream outputStream = System.out;
    out = new PrintWriter(outputStream);

    int N = in.nextInt();
    int M = in.nextInt();
    int Q = in.nextInt();
    createGraph(N);

    for (int i = 0; i < M; i++) {
      int U = in.nextInt();
      int V = in.nextInt();
      int T = in.nextInt();
      addEdge(U, V, T);
    }
    while (Q-- > 0) {
      int X = in.nextInt();
      int Y = in.nextInt();
      int K = in.nextInt();
      canMudik(X, Y, K);
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

  }
}