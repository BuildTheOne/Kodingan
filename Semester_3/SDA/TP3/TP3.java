package SDA.TP3;
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
    List<Employee> friendList = new ArrayList<>(); // aka adjacency list dalam representasi graph network

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
    // Insert method untuk melakukan heapify up jika ada teman baru
    void insert(Employee V) {
      // Tambahkan teman ke dalam friendList
      // Teman baru akan ditambahkan di bagian belakang list
      friendList.add(V);

      // Ya, kalau friend 0 tidak usah ada heapify :)
      if (friendList.size() != 0) {
        int i = friendList.size() - 1;
        // While loop untuk mengecek apakah ranking teman baru lebih besar dari heap
        // parent
        // Jika iya, maka ada swap antara teman baru dengan parent
        while (friendList.get(i).rank.rank > friendList.get((i - 1) / 2).rank.rank) {
          Employee tmp;
          tmp = friendList.get(i);
          friendList.set(i, friendList.get((i - 1) / 2));
          friendList.set(((i - 1) / 2), tmp);
          i = (i - 1) / 2;
        }
      }

      // Jika ranking teman baru lebih besar atau sama dengan dia, otomatis dia jadi rentan
      if (V.rank.rank >= rank.rank) {
        rentan = true;
      }
    }

    // Sumber ide: http://btv.melezinek.cz/binary-heap.html
    // (Cuma di bagian heapify down, bagian yg lain ide sendiri)
    // Delete method untuk melakukan heapify down jika ada teman yang dihapus
    void delete(Employee U) {
      // Kalau friendnya jadi 0 setelah resign, ya keluarkan saja, tidak perlu heap :)
      if (friendList.size() == 1) {
        friendList.remove(0);
      } else {
        // 1. Cari index dari teman yang resign
        // (Karena pake arraylist, jadi harus loop untuk mencarinya)
        int index = 0;
        for (int i = 0; i < friendList.size(); i++) {
          if (friendList.get(i).equals(U)) {
            index = i;
            break;
          }
        }
        // 2. Jika teman yg resign ada di tengah2, maka heapify down
        if (index != friendList.size() - 1) {
          // Swap teman yang dihapus dengan teman yg ada di akhir list
          friendList.set(index, friendList.get(friendList.size() - 1));
          friendList.remove(friendList.size() - 1);

          // Heap down:
          // Loop selama masih ada dalam jangkauan jumlah teman
          while (index < friendList.size()) {
            // Ada 3 cases: 1. jika yg di swap ini punya left dan right child:
            if (index * 2 + 2 < friendList.size()) {
              // Jika yg di swap lebih kecil dari left dan right child, pilih yang lebih
              // tinggi lalu swap
              if (friendList.get(index).rank.rank < friendList.get(index * 2 + 1).rank.rank
                  && friendList.get(index).rank.rank < friendList.get(index * 2 + 2).rank.rank) {
                if (friendList.get(index * 2 + 1).rank.rank > friendList.get(index * 2 + 2).rank.rank) {
                  Employee tmp = friendList.get(index);
                  friendList.set(index, friendList.get(index * 2 + 1));
                  friendList.set(index * 2 + 1, tmp);
                  index = index * 2 + 1;
                } else {
                  Employee tmp = friendList.get(index);
                  friendList.set(index, friendList.get(index * 2 + 2));
                  friendList.set(index * 2 + 2, tmp);
                  index = index * 2 + 2;
                }
              } else if (friendList.get(index).rank.rank < friendList.get(index * 2 + 1).rank.rank) {
                // Jika hanya left child yang lebih besar, swap
                Employee tmp = friendList.get(index);
                friendList.set(index, friendList.get(index * 2 + 1));
                friendList.set(index * 2 + 1, tmp);
                index = index * 2 + 1;
              } else if (friendList.get(index).rank.rank < friendList.get(index * 2 + 2).rank.rank) {
                // Yang hanya right child yang lebih besar, swap
                Employee tmp = friendList.get(index);
                friendList.set(index, friendList.get(index * 2 + 2));
                friendList.set(index * 2 + 2, tmp);
                index = index * 2 + 2;
              } else {
                // Jika sudah lebih kecil dari kedua child, keluar dari loop
                break;
              }
            } else if (index * 2 + 1 < friendList.size()) {
              // 2. Jika hanya ada left child, swap
              if (friendList.get(index).rank.rank < friendList.get(index * 2 + 1).rank.rank) {
                Employee tmp = friendList.get(index);
                friendList.set(index, friendList.get(index * 2 + 1));
                friendList.set(index * 2 + 1, tmp);
                index = index * 2 + 1;
                index = index * 2 + 1;
              } else {
                break;
              }
            } else {
              // 3. Kalo mentok udh di paling akhir list
              break;
            }
          }
        } else {
          // 3. Kalau temannya ada di akhir list, ya hapus saja, tidak perlu heap
          friendList.remove(friendList.size() - 1);
        }

      }

      // Jika ternyata karyawan ini rentan, cek apakah resign teman tadi menyebabkan
      // karyawan ini jadi tidak rentan
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
    // Menambah teman di masing2 karyawan
    // (Karyawan U menambah V, karyawan V menambah U)
    // Tiap karyawan melakukan heapify untuk mengurutkan teman berdasarkan ranking
    // (Lebih lengkap di method insert())
    U.insert(V);
    V.insert(U);
  }

  // RESIGN
  static void handleResign(Employee U) {
    U.alreadyResign = true;
    U.rank.employeeList.remove(U);
    // Loop seluruh teman U untuk menghapus U
    for (int i = 0; i < U.friendList.size(); i++) {
      U.friendList.get(i).delete(U);
    }
    employee--;
  }

  // CARRY
  static void handleCarry(Employee U) {
    // Yah, karena friendList sudah pakai max Heap, ambil yang paling pertama
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
      // Jika U sudah punya boss, keluarkan
      if (U.boss != null) {
        out.println(U.boss.boss.rank.rank);
      } else {
        // Jika tidak, traversal network dengan bfs
        // Masukkan diri sendiri pertama pertama ke queue
        Queue<Employee> queue = new ArrayDeque<>();
        boolean visited[] = new boolean[employeeList.size() + 1];
        queue.add(U);

        Boss boss = new Boss();
        U.boss = boss;
        boss.boss = U;
        boss.secondInCommand = U.friendList.get(0);

        // While loop queue, cek friendList, masukan ke queue
        while (!queue.isEmpty()) {
          Employee e = queue.poll();
          visited[e.index] = true;
          for (int i = 0; i < e.friendList.size(); i++) {
            Employee f = e.friendList.get(i);
            f.boss = boss;
            if (!visited[f.index]) {
              // Cek apakah sudah dikunjungi
              if (boss.boss.rank.rank <= f.rank.rank) {
                // Jika ranking boss lebih besar, swap
                // Yg sebelumnya paling tinggi jadi kedua tertinggi
                boss.secondInCommand = boss.boss;
                boss.boss = f;
              }
              visited[f.index] = true;
              queue.add(f);
            }
          }
        }

        // Untuk karyawan yang juga adalah boss, jadikan kedua tertinggi sebagai boss
        // (Karena network tidak termasuk dirinya sendiri)
        Employee scnd = boss.secondInCommand;
        Boss highest = new Boss();
        highest.boss = scnd;
        boss.boss.boss = highest;

        out.println(U.boss.boss.rank.rank);
      }
    }
  }

  // SEBAR (Credit to: Anisa Maharani)
  static void handleSebar(Employee U, Employee V) {
    if (U.rank == V.rank) {
      // Jika rankingnya sudah sama, maka keluarkan 0
      // (Butuh 0 perantara kalau rankingnya sama kan?)
      out.println(0);
    } else {
      // Traversal network dengan dfs
      // Masukan ke queue
      Queue<Employee> queue = new ArrayDeque<>();
      boolean visited[] = new boolean[100001];
      boolean visitedRank[] = new boolean[100001];
      Integer sum[] = new Integer[100001];
      queue.add(U);
      sum[U.index] = -1;

      // While loop queue
      while (!queue.isEmpty()) {
        Employee e = queue.poll();
        visited[e.index] = true;

        // Ranking dulu baru teman karena umumnya, 
        // lewat ranking butuh lebih sedikit perantara daripada lewat teman,
        // sehingga jika sudah dikunjungi via ranking, tidak perlu dicek lagi 
        // jika dilewati via teman

        if (!visitedRank[e.rank.rank]) {
          // Traversal berdasarkan ranking
          for (int i = 0; i < e.rank.employeeList.size(); i++) {
            Employee f = e.rank.employeeList.get(i);
            // Cek apakah sudah dikunjungi
            if (!visited[f.index]) {
              visited[f.index] = true;
              // Increment perantara minimal
              sum[f.index] = sum[e.index] + 1;
              visitedRank[f.rank.rank] = true;
              if (f.equals(V)) {
                // Namun jika ternyata sama, break dari loop
                break;
              }
              queue.add(f); // Masukkan ke queue
            }
          }
        }

        // Traversal berdasarkan friendList
        for (int i = 0; i < e.friendList.size(); i++) {
          Employee f = e.friendList.get(i);
          // Cek apakah sudah dikunjungi
          if (!visited[f.index]) {
            visited[f.index] = true;
            // Increment perantara minimal
            sum[f.index] = sum[e.index] + 1;
            if (f.equals(V)) {
              // Namun jika ternyata sama, break dari loop
              break;
            }
            queue.add(f); // Masukkan ke queue
          }
        }
      }
      // Jika queue sudah habis dan karyawan yang dicari ada di network (udah dikunjungi),
      // Keluarkan jumlah perantara minimal 
      if (queue.isEmpty() && visited[V.index]) {
        out.println(sum[V.index]);
      } else {
        out.println(-1);
      }
    }
  }

  // SIMULASI
  static void handleSimulasi() {
    // Loop semua karyawan untuk mencari siapa saja yang tidak rentan,
    // lalu keluarkan jumlahnya
    int countRentan = employee;
    for (int i = 0; i < employeeList.size(); i++) {
      if (!employeeList.get(i).alreadyResign) {
        if (employeeList.get(i).rentan) {
          countRentan--;
        }
      }
    }
    out.println(countRentan);
  }

  // NETWORKING
  static void handleNetworking() {
    // Yah, gitu deh
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