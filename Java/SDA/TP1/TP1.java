package SDA.TP1;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class TP1 {

  private static InputReader in;
  private static PrintWriter out;

  static class Student {
    String name;
    String code;
    int call;
    int rank;
    int prevRank;
    boolean rankUp;

    public Student(String name, String code, int rank) {
      this.name = name;
      this.code = code;
      this.rank = rank;
      this.prevRank = rank;
      this.rankUp = false;
    }

    public void setCall(int i) {
      call += i;
    }

    public String toString() {
      return this.name;
    }
  }

  static void handlePanutan(List<Integer[]> listTukang, int num) {
    Integer[] s = listTukang.get(num - 1);
    out.println(s[0] + " " + s[1]);
  }

  static void handleKompetitif(List<Student> students, int index) {
    Student s = students.get(index);
    out.println(s.name + " " + s.call);
  }

  static void handleEvaluasi(List<Student> studentEval) {
    if (studentEval.size() == 0) {
      out.println("TIDAK ADA");
    } else {
      out.println(studentEval.toString().replace(", ", " ").replace("[", "").replace("]", ""));
    }
  }

  static void handleDuo(List<Student> students, Queue<Student> kangBaksoList, Queue<Student> kangSiomayList) {
    for (int i = 0; i < students.size(); i++) {
      if (kangBaksoList.size() != 0 && kangSiomayList.size() != 0) {
        out.println(kangBaksoList.remove() + " " + kangSiomayList.remove());
        if (kangBaksoList.size() == 0 && kangSiomayList.size() == 0)
          break;
      } else {
        out.print("TIDAK DAPAT: ");
        if (kangBaksoList.size() == 0) {
          out.println(kangSiomayList.toString().replace(", ", " ").replace("[", "").replace("]", ""));
        } else {
          out.println(kangBaksoList.toString().replace(", ", " ").replace("[", "").replace("]", ""));
        }
        break;
      }
    }
  }

  static void handleDeploy(List<Student> students, int num) {
    // out.print(num % 1000000007);
    out.println(num);
  }

  public static void main(String args[]) throws IOException {

    InputStream inputStream = System.in;
    in = new InputReader(inputStream);
    OutputStream outputStream = System.out;
    out = new PrintWriter(outputStream);

    // List to contain the students declaration
    List<Student> students = new ArrayList<Student>();
    List<Integer[]> listTukang = new ArrayList<>();
    List<Student> studentEval = new ArrayList<>();
    Queue<Student> kangBaksoList = new LinkedList<>();
    Queue<Student> kangSiomayList = new LinkedList<>();
    int indexCall = 0;

    // Batch
    int C = in.nextInt();
    for (int i = 0; i < C; i++) {
      // Add the students
      int N = in.nextInt();
      for (int j = 0; j < N; j++) {
        String studentName = in.next();
        String studentCode = in.next();
        students.add(new Student(studentName, studentCode, students.size()));
      }

      // Looping for each day
      int E = in.nextInt();
      for (int k = 0; k < E; k++) {
        int P = in.nextInt();
        for (int l = 0; l < P; l++) {
          String inputName = in.next();
          int inputRank = in.nextInt();

          // Change students position
          // Get student
          Student s = students.get(0);
          for (int a = 0; a < students.size(); a++) {
            if (students.get(a).name.equals(inputName)) {
              s = students.remove(a);
              break;
            }
          }

          // Put the student on first or last ranking
          if (inputRank == 0) {
            students.add(0, s);
          } else {
            students.add(s);
          }
          s.setCall(1);
        }

        // Adjust many things
        for (int c = 0; c < students.size(); c++) {
          Student s = students.get(c);
          s.prevRank = s.rank;
          s.rank = c;
          if (s.rankUp == false) {
            if (s.rank < s.prevRank) {
              s.rankUp = true;
            }
          }

          if (k == E - 1) {
            if (c == 0) {
              listTukang.clear();
              kangBaksoList.clear();
              kangSiomayList.clear();
            }

            if (!s.rankUp) {
              studentEval.add(s);
            }

            if (s.code.equals("B")) {
              kangBaksoList.add(s);
              if (listTukang.size() == 0) {
                listTukang.add(new Integer[] { 1, 0 });
              } else {
                Integer[] prev = listTukang.get(c - 1);
                int prevB = prev[0] + 1;
                int prevS = prev[1];
                listTukang.add(new Integer[] { prevB, prevS });
              }
            } else {
              kangSiomayList.add(s);
              if (listTukang.size() == 0) {
                listTukang.add(new Integer[] { 0, 1 });
              } else {
                Integer[] prev = listTukang.get(c - 1);
                int prevB = prev[0];
                int prevS = prev[1] + 1;
                listTukang.add(new Integer[] { prevB, prevS });
              }
            }

            if (s.call > students.get(indexCall).call) {
              indexCall = c;
            }
          }
        }

        out.println(students.toString().replace(", ", " ").replace("[", "").replace("]", ""));
      }

      String evaluate = in.next();
      if (evaluate.equals("PANUTAN")) {
        int num = in.nextInt();
        handlePanutan(listTukang, num);
      } else if (evaluate.equals("KOMPETITIF")) {
        handleKompetitif(students, indexCall);
      } else if (evaluate.equals("EVALUASI")) {
        handleEvaluasi(studentEval);
      } else if (evaluate.equals("DUO")) {
        handleDuo(students, kangBaksoList, kangSiomayList);
      } else {
        int num = in.nextInt();
        handleDeploy(students, num);
      }
      students.clear();
      studentEval.clear();
      indexCall = 0;
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