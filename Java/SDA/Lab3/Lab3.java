import java.io.*;
import java.util.*;
import static java.lang.Math.min;
import static java.lang.Math.max;

/*
 
Solution optimal. Idenya cari DP[i][k] untuk semua k. Lalu compare dan nilai K bikin max yang diambil
 
 
*/

class res {
  long value;
  int num;

  public res(long _value, int _num) {
    value = _value;
    num = _num;
  }
}

public class Lab3 {
  private static InputReader in = new InputReader(System.in);
  private static PrintWriter out = new PrintWriter(System.out);

  static ArrayList<Integer> B = new ArrayList<>();
  static ArrayList<Integer> S = new ArrayList<>();
  static ArrayList<Integer> Bonus = new ArrayList<>();

  /**
   * The main method that reads input, calls the function for each question's
   * query, and output the results.
   * 
   * @param args Unused.
   * @return Nothing.
   */
  public static void main(String[] args) {

    int N = in.nextInt();

    for (int i = 0; i < N; i++) {
      int temp = in.nextInt();
      B.add(temp);
    }

    for (int i = 0; i < N; i++) {
      int temp = in.nextInt();
      S.add(temp);
    }

    for (int i = 0; i < N; i++) {
      int temp = in.nextInt();
      Bonus.add(temp);
    }

    res r = getMaxDiamond();
    out.println(r.value + " " + r.num);
    out.close();
  }

  static long dp[][][];
  static boolean hascal[][][];

  // lst = 0: bolos, lst = 1: siang, lst = 2: malam
  private static long rekurs(int pos, int ambil, int lst) {

    // base case
    if (ambil < 0) {
      return -1000000000000000000L;
    }

    // basecase, harus exactly ngambil sebanyak "ambil"
    if (pos == B.size()) {
      if (ambil == 0)
        return 0;
      else
        return -1000000000000000000L;
    }

    if (hascal[pos][ambil][lst]) {
      return dp[pos][ambil][lst];
    }

    long ans = -1000000000000000000L;

    // case 1: ambil siang
    if (lst != 1) {
      ans = Math.max(ans, rekurs(pos + 1, ambil - 1, 1) + B.get(pos));
    }

    // case 2: ambil malam
    if (lst != 2) {
      ans = Math.max(ans, rekurs(pos + 1, ambil - 1, 2) + S.get(pos));
    }

    // case 3: bolos
    ans = Math.max(ans, rekurs(pos + 1, ambil, 0));

    hascal[pos][ambil][lst] = true;
    dp[pos][ambil][lst] = ans;

    return ans;

  }

  public static res getMaxDiamond() {

    int N = B.size();

    dp = new long[N + 1][N + 1][3];
    hascal = new boolean[N + 1][N + 1][3];

    long mx = 0;
    int num = 0;

    for (int temp = 1; temp <= N; temp++) {
      long berlian = rekurs(0, temp, 0) + Bonus.get(temp - 1);

      if (berlian > mx) {
        mx = berlian;
        num = temp;
      }
    }

    return new res(mx, num);
  }

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
