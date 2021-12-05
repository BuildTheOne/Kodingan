import java.io.*;
import java.util.*;

public class TP3 {
  private static InputReader in;
  private static PrintWriter out;

  static List<Employee> employeeList = new ArrayList<>();
  static Rank rankList[] = new Rank[100001];
  static int employee = 0;

  static class Rank {
    int rank;
    List<Employee> employeeList = new ArrayList<>();

    public Rank(int rank) {
      this.rank = rank;
    }
  }

  static class Boss {
    Employee boss;
    Employee secondInCommand;

    public Boss() {
    }
  }

  static class Employee {
    int index;
    Rank rank;
    List<Employee> friendList = new ArrayList<>();

    boolean alreadyResign;
    Boss boss;
    boolean rentan;

    public Employee(int index, int rank) {
      this.index = index;
      if (rankList[rank] == null) {
        this.rank = new Rank(rank);
        rankList[rank] = this.rank;
      } else {
        this.rank = rankList[rank];
      }
      this.rank.employeeList.add(this);
      alreadyResign = false;
      boss = null;
      rentan = false;
    }

    // Heap operation
    void insert(Employee V) {
      friendList.add(V);

      if (friendList.size() != 0) {
        int i = friendList.size() - 1;
        while (friendList.get(i).rank.rank > friendList.get((i - 1) / 2).rank.rank) {
          Employee tmp;
          tmp = friendList.get(i);
          friendList.set(i, friendList.get((i - 1) / 2));
          friendList.set(((i - 1) / 2), tmp);
          i = (i - 1) / 2;
        }
      }

      // out.println(printFriends());

      if (V.rank.rank >= rank.rank) {
        rentan = true;
      }
    }

    // Sumber ide: http://btv.melezinek.cz/binary-heap.html
    // (Cuma di bagian heapify down, bagian yg lain ide sendiri)
    void delete(Employee U) {
      if (friendList.size() == 1) {
        friendList.remove(0);
      } else {
        int index = 0;
        for (int i = 0; i < friendList.size(); i++) {
          if (friendList.get(i).equals(U)) {
            index = i;
            break;
          }
        }
        // out.println(printFriends());
        if (index != friendList.size() - 1) {
          friendList.set(index, friendList.get(friendList.size() - 1));
          friendList.remove(friendList.size() - 1);

          while (index < friendList.size()) {
            if (index * 2 + 2 < friendList.size()) {
              if (friendList.get(index).rank.rank < friendList.get(index * 2 + 1).rank.rank
                  && friendList.get(index).rank.rank < friendList.get(index * 2 + 2).rank.rank) {
                if (friendList.get(index * 2 + 1).rank.rank > friendList.get(index * 2 + 2).rank.rank) {
                  // out.println(index);
                  Employee tmp = friendList.get(index);
                  friendList.set(index, friendList.get(index * 2 + 1));
                  friendList.set(index * 2 + 1, tmp);
                  index = index * 2 + 1;
                } else {
                  // out.println(index);
                  Employee tmp = friendList.get(index);
                  friendList.set(index, friendList.get(index * 2 + 2));
                  friendList.set(index * 2 + 2, tmp);
                  index = index * 2 + 2;
                }
              } else if (friendList.get(index).rank.rank < friendList.get(index * 2 + 1).rank.rank) {
                Employee tmp = friendList.get(index);
                friendList.set(index, friendList.get(index * 2 + 1));
                friendList.set(index * 2 + 1, tmp);
                index = index * 2 + 1;
              } else if (friendList.get(index).rank.rank < friendList.get(index * 2 + 2).rank.rank) {
                Employee tmp = friendList.get(index);
                friendList.set(index, friendList.get(index * 2 + 2));
                friendList.set(index * 2 + 2, tmp);
                index = index * 2 + 2;
              } else {
                break;
              }
            } else if (index * 2 + 1 < friendList.size()) {
              if (friendList.get(index).rank.rank < friendList.get(index * 2 + 1).rank.rank) {
                // out.println(index);
                Employee tmp = friendList.get(index);
                friendList.set(index, friendList.get(index * 2 + 1));
                friendList.set(index * 2 + 1, tmp);
                index = index * 2 + 1;
                index = index * 2 + 1;
              } else {
                break;
              }
            } else {
              break;
            }
          }
        } else {
          friendList.remove(friendList.size() - 1);
        }

        // out.println(printFriends());
      }

      if (friendList.size() == 0) {
        rentan = false;
      } else {
        if (rentan) {
          boolean ifHigher = false;
          for (int i = 0; i < friendList.size(); i++) {
            if (friendList.get(i).rank.rank >= rank.rank) {
              ifHigher = true;
              break;
            }
          }
          if (ifHigher) {
            rentan = true;
          } else {
            rentan = false;
          }
        }
      }

    }

    // Print methods just for debug
    public String toString() {
      return Integer.toString(rank.rank);
    }

    public String printFriends() {
      return "Friends of rank " + rank + ": " + friendList.toString();
    }
  }

  // TAMBAH
  static void handleTambah(Employee U, Employee V) {
    U.insert(V);
    V.insert(U);

  }

  // RESIGN
  static void handleResign(Employee U) {
    U.alreadyResign = true;
    U.rank.employeeList.remove(U);
    for (int i = 0; i < U.friendList.size(); i++) {
      U.friendList.get(i).delete(U);
    }
    employee--;
  }

  // CARRY
  static void handleCarry(Employee U) {
    if (U.friendList.size() == 0) {
      out.println(0);
    } else {
      out.println(U.friendList.get(0));
    }
  }

  // BOSS
  static void handleBoss(Employee U) {
    if (U.friendList.size() == 0) {
      out.println(0);
    } else {
      if (U.boss != null) {
        // out.println("Boss: " + U.boss.boss);
        // out.println("Second: " + U.boss.secondInCommand);
        out.println(U.boss.boss.rank.rank);
      } else {

        Queue<Employee> queue = new ArrayDeque<>();
        boolean visited[] = new boolean[employeeList.size() + 1];
        queue.add(U);

        Boss boss = new Boss();
        U.boss = boss;
        boss.boss = U;
        boss.secondInCommand = U.friendList.get(0);

        while (!queue.isEmpty()) {
          Employee e = queue.poll();
          // out.println(e);
          visited[e.index] = true;
          for (int i = 0; i < e.friendList.size(); i++) {
            Employee f = e.friendList.get(i);
            f.boss = boss;
            if (!visited[f.index]) {
              if (boss.boss.rank.rank <= f.rank.rank) {
                // out.println(f.boss.boss);
                // out.println(f.boss.secondInCommand);
                boss.secondInCommand = boss.boss;
                boss.boss = f;
              }
              visited[f.index] = true;
              queue.add(f);
            }
          }
        }

        Employee scnd = boss.secondInCommand;
        Boss highest = new Boss();
        highest.boss = scnd;
        boss.boss.boss = highest;

        // out.println("Boss: " + U.boss.boss);
        // out.println("Second: " + U.boss.secondInCommand);
        out.println(U.boss.boss.rank.rank);

      }
    }
  }

  // SEBAR (Credit to: Anisa Maharani)
  static void handleSebar(Employee U, Employee V) {
    if (U.rank == V.rank) {
      out.println(0);
    } else {
      Queue<Employee> queue = new ArrayDeque<>();
      boolean visited[] = new boolean[100001];
      boolean visitedRank[] = new boolean[100001];
      Integer sum[] = new Integer[100001];
      queue.add(U);
      sum[U.index] = -1;

      while (!queue.isEmpty()) {
        Employee e = queue.poll();
        // out.println(e.index);
        visited[e.index] = true;

        if (!visitedRank[e.rank.rank]) {
          for (int i = 0; i < e.rank.employeeList.size(); i++) {
            Employee f = e.rank.employeeList.get(i);
            // out.println(f.index);
            if (!visited[f.index]) {
              visited[f.index] = true;
              sum[f.index] = sum[e.index] + 1;
              visitedRank[f.rank.rank] = true;
              if (f.equals(V)) {
                break;
              }
              queue.add(f);
            }
          }
        }

        for (int i = 0; i < e.friendList.size(); i++) {
          Employee f = e.friendList.get(i);
          // out.println(f.index);
          if (!visited[f.index]) {
            visited[f.index] = true;
            sum[f.index] = sum[e.index] + 1;
            if (f.equals(V)) {
              break;
            }
            queue.add(f);
          }
        }
      }
      if (queue.isEmpty() && visited[V.index]) {
        // out.println("Butuh segini: " + sum[V.index]);
        out.println(sum[V.index]);
      } else {
        // out.println("Gak bisa");
        out.println(-1);
      }
    }
  }

  // SIMULASI
  static void handleSimulasi() {
    int countRentan = employee;
    for (int i = 0; i < employeeList.size(); i++) {
      if (!employeeList.get(i).alreadyResign) {
        // out.println((employeeList.get(i).index + 1) + ": " + employeeList.get(i).rentan);
        if (employeeList.get(i).rentan) {
          countRentan--;
        }
      }
    }
    // out.println("Sisa karyawan: " + countRentan);
    out.println(countRentan);
  }

  // NETWORKING
  static void handleNetworking() {
    out.println(0);
  }

  public static void main(String args[]) throws IOException {

    InputStream inputStream = System.in;
    in = new InputReader(inputStream);
    OutputStream outputStream = System.out;
    out = new PrintWriter(outputStream);

    int N = in.nextInt();
    int M = in.nextInt();
    int Q = in.nextInt();
    for (int i = 0; i < N; i++) {
      int P = in.nextInt();
      // add new employee
      employeeList.add(new Employee(i, P));
      employee++;
    }

    // Add connection
    while (M-- > 0) {
      int UInput = in.nextInt();
      int VInput = in.nextInt();
      Employee U = employeeList.get(UInput - 1);
      Employee V = employeeList.get(VInput - 1);
      handleTambah(U, V);
    }

    while (Q-- > 0) {
      int command = in.nextInt();
      if (command == 1) { // TAMBAH
        int UInput = in.nextInt();
        int VInput = in.nextInt();
        Employee U = employeeList.get(UInput - 1);
        Employee V = employeeList.get(VInput - 1);
        handleTambah(U, V);
      } else if (command == 2) { // RESIGN
        int UInput = in.nextInt();
        Employee U = employeeList.get(UInput - 1);
        handleResign(U);
      } else if (command == 3) { // CARRY
        int UInput = in.nextInt();
        Employee U = employeeList.get(UInput - 1);
        handleCarry(U);
      } else if (command == 4) { // BOSS
        int UInput = in.nextInt();
        Employee U = employeeList.get(UInput - 1);
        handleBoss(U);
      } else if (command == 5) { // SEBAR
        int UInput = in.nextInt();
        int VInput = in.nextInt();
        Employee U = employeeList.get(UInput - 1);
        Employee V = employeeList.get(VInput - 1);
        handleSebar(U, V);
      } else if (command == 6) { // SIMULASI
        handleSimulasi();
      } else if (command == 7) { // NETWORKING
        handleNetworking();
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
  }
}