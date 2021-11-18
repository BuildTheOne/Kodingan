import java.io.*;
import java.util.*;

public class TP2 {

  private static InputReader in;
  private static PrintWriter out;

  static Map<String, Island> listIsland = new HashMap<>();
  static Map<String, Land> listShrine = new HashMap<>();
  static Shogun raiden = new Shogun();

  static class Shogun {
    Island positionIsland;
    Land positionLand;

    public Shogun() {
      positionIsland = null;
      positionLand = null;
    }

    public String toString() {
      return ("Shogun position: " + positionIsland + " " + positionLand);
    }
  }

  static class Height {
    Long height;
    Long current;
    Height left;
    Height right;
    Long countIsland;
    Land heightFirst;
    Land heightLast;

    public Height(Long height) {
      this.height = height;
      current = height;
      left = null;
      right = null;
      countIsland = 1L;
      heightFirst = null;
      heightLast = null;
    }

    public String toString() {
      return Long.toString(current);
    }
  }

  static class Land {
    Height height;
    Land prev;
    Land next;
    Boolean hasShrine;
    String nameShrine;
    Island island;
    Land prevHeight;
    Land nextHeight;

    public Land() {
      height = null;
      prev = null;
      next = null;
      hasShrine = false;
      nameShrine = "";
      island = null;
      prevHeight = null;
      nextHeight = null;
    }

    public String toString() {
      return height.toString();
    }
  }

  static class Island {
    String nameIsland;
    Island head;
    Island tail;
    Land first;
    Land last;
    Long length;
    Land firstLocal;
    Land position;
    Map<Long, Height> listHeight = new HashMap<>();

    public Island(String nameIsland, Long length) {
      this.nameIsland = nameIsland;
      this.length = length;
      head = null;
      tail = null;
      first = null;
      last = null;
      firstLocal = null;
      position = null;
    }

    // Utils
    public Long totalIsland() {
      Long sum = length;
      if (tail == null)
        return sum;
      else
        return sum += tail.totalIsland();
    }

    public Land startPosition(int E) {
      position = first;
      for (int i = 0; i < E - 1; i++) {
        position = position.next;
      }
      return position;
    }

    // Add Operations
    public void addLand(Long height) {
      // Initialize new Land
      Land newLand = new Land();
      // Add Land as linked list
      if (first == null) {
        first = newLand;
        last = newLand;
        position = newLand;
        newLand.hasShrine = true;
        newLand.nameShrine = nameIsland;
        firstLocal = newLand;
        listShrine.put(nameIsland, newLand);
      } else {
        if (raiden.positionLand == null) {
          position = last;
          position.next = newLand;
          newLand.prev = position;
          position = newLand;
          last = position;
        }
      }
      newLand.island = this;
      // Add height as BST
      if (listHeight.get(height) != null) {
        Height hgt = listHeight.get(height);
        newLand.height = hgt;
        hgt.countIsland++;
        Land currLast = hgt.heightLast;
        newLand.prevHeight = currLast;
        currLast.nextHeight = newLand;
        hgt.heightLast = newLand;
      } else {
        Height newHeight = new Height(height);
        newLand.height = newHeight;
        addHeight(first.height, newHeight);
        listHeight.put(height, newHeight);
        newHeight.heightFirst = newLand;
        newHeight.heightLast = newLand;
      }
      if (raiden.positionLand != null) {
        if (raiden.positionLand == last) {
          position = last;
          position.next = newLand;
          newLand.prev = position;
          position = newLand;
          last = position;
        } else {
          position = raiden.positionLand;
          newLand.next = position.next;
          newLand.prev = position;
          position.next.prev = newLand;
          position.next = newLand;
        }
        out.println(newLand.height);
      }
    }

    public Height addHeight(Height root, Height height) {
      if (root == null)
        return height;

      if (root.height > height.height) {
        root.left = addHeight(root.left, height);
      } else if (root.height < height.height) {
        root.right = addHeight(root.right, height);
      }
      return root;
    }

    // Handle Commands
    public void handleRise(Long H, Long X) {
      Height root = first.height;
      Long count = findRise(root, H, X);
      out.println(count);
      // printTreePreorder(root);
      // out.println(listHeight);
    }

    public Long findRise(Height root, Long H, Long X) {
      Long count = 0L;
      if (root != null) {
        if (root.current <= H) {
          count += findRise(root.right, H, X);
        } else {
          count += findRise(root.left, H, X);
          count += findRise(root.right, H, X);
          count += root.countIsland;
          // listHeight.remove(root.height);
          root.current += X;
          // listHeight.put(root.height, root);
        }
      }
      return count;
    }

    public void handleQuake(Long H, Long X) {
      Height root = first.height;
      Long count = findQuake(root, H, X);
      out.println(count);
      // printTreePreorder(root);
      // out.println(listHeight);
    }

    public Long findQuake(Height root, Long H, Long X) {
      Long count = 0L;
      if (root != null) {
        if (root.current < H) {
          count += findQuake(root.left, H, X);
          count += findQuake(root.right, H, X);
          count += root.countIsland;
          // listHeight.remove(root.height);
          root.current -= X;
          // listHeight.put(root.height, root);
        } else {
          count += findQuake(root.left, H, X);
        }
      }
      return count;
    }

    public void handleSweeping(Long L) {
      Height root = first.height;
      Long count = findSweep(root, L);
      out.println(count);
      // printTreePreorder(root);
      // out.println(listHeight);
    }

    public Long findSweep(Height root, Long L) {
      Long count = 0L;
      if (root != null) {
        if (root.height < L) {
          count += findSweep(root.left, L);
          count += findSweep(root.right, L);
          count += root.countIsland;
        } else {
          count += findSweep(root.left, L);
        }
      }
      return count;
    }

    public void handleGerak(String direction, Long S) {
      // out.println(printIsland());
      position = raiden.positionLand;
      if (direction.equals("KIRI")) {
        for (int i = 0; i < S; i++) {
          if (position.prev == null) {
            break;
          }
          position = position.prev;
        }
      } else {
        for (int i = 0; i < S; i++) {
          if (position.next == null) {
            break;
          }
          position = position.next;
        }
      }
      raiden.positionLand = position;
      raiden.positionIsland = position.island;
      out.println(raiden.positionLand.height);
    }

    public void handleStabilize() {
      Land current = raiden.positionLand;
      if (!current.hasShrine) {
        Long height = Math.min(current.height.height, current.prev.height.height);
        addLand(height);
        length++;
      } else {
        out.println(0);
      }
    }

    public void handleCrumble() {
      Land current = raiden.positionLand;
      if (!current.hasShrine) {
        out.println(current.height);
        Height hgt = current.height;
        if (hgt.countIsland == 1L) {
          hgt.countIsland = 0L;
        } else {
          hgt.countIsland--;
        }
        if (current.nextHeight != null && current.prevHeight != null) {
          if (current == hgt.heightFirst) {
            hgt.heightFirst = current.nextHeight;
            current.nextHeight.prevHeight = null;
          } else if (current == hgt.heightLast) {
            hgt.heightLast = current.prevHeight;
            current.prevHeight.nextHeight = null;
          } else {
            current.nextHeight.prevHeight = current.prevHeight;
            current.prevHeight.nextHeight = current.nextHeight;
          }
        }
        if (current == last) {
          current.prev.next = null;
          last = current.prev;
        } else {
          current.prev.next = current.next;
          current.next.prev = current.prev;
        }
        current = current.prev;
        raiden.positionLand = current;
        raiden.positionIsland = current.island;
        length--;
      } else {
        out.println(0);
      }
      // out.println(printIsland());
    }

    public void handleTebas(String direction, Long S) {
      Land raidenLocationLand = raiden.positionLand;
      // out.println(raidenLocationLand.nextHeight);
      if (direction.equals("KIRI")) {
        if (raidenLocationLand.prevHeight == null) {
          out.println(0);
        } else {
          for (int i = 0; i < S; i++) {
            if (raidenLocationLand.prevHeight == null)
              break;
            raidenLocationLand = raidenLocationLand.prevHeight;
          }
          raiden.positionLand = raidenLocationLand;
          raiden.positionIsland = raidenLocationLand.island;
          out.println(raidenLocationLand.next.height);
        }
      } else {
        if (raidenLocationLand.nextHeight == null) {
          out.println(0);
        } else {
          for (int i = 0; i < S; i++) {
            if (raidenLocationLand.nextHeight == null)
              break;
            raidenLocationLand = raidenLocationLand.nextHeight;
          }
          raiden.positionLand = raidenLocationLand;
          raiden.positionIsland = raidenLocationLand.island;
          out.println(raidenLocationLand.prev.height);
        }
      }
    }

    public void handleUnifikasi(Island newIsland) {
      newIsland.head = last.island;
      last.island.tail = newIsland;
      last.next = newIsland.first;
      newIsland.first.prev = last;
      newIsland.first = first;
      last = newIsland.last;
      out.println(totalIsland());
      // out.println(printTail());
    }

    public void handlePisah() {
      Island prevIsl = firstLocal.prev.island.first.island;
      firstLocal.prev.island.tail = null;
      head = null;
      prevIsl.last = firstLocal.prev;
      prevIsl.last.next= null;
      firstLocal.prev = null;
      first = firstLocal;
      out.println(prevIsl.totalIsland() + " " + totalIsland());
      // out.println(prevIsl.printTail());
      // out.println(printTail());
    }

    // Print methods just for debug
    public String toString() {
      return nameIsland;
    }

    public String printIsland() {
      position = first;
      StringBuilder s = new StringBuilder();
      while (position != null) {
        s.append(position + " ");
        position = position.next;
      }
      return s.toString();
    }

    public String printTail() {
      Island i = this;
      StringBuilder s = new StringBuilder();
      while (i != null) {
        s.append(i + " ");
        i = i.tail;
      }
      return s.toString();
    }

    public void printTreePreorder(Height root) {
      if (root != null) {
        if (root.countIsland > 0) {
          // out.print(root + " ");
          out.print(root + ": " + root.countIsland + "\n");
          printTreePreorder(root.left);
          printTreePreorder(root.right);
        }
      }
    }
  }

  public static void main(String args[]) throws IOException {
    InputStream inputStream = System.in;
    in = new InputReader(inputStream);
    OutputStream outputStream = System.out;
    out = new PrintWriter(outputStream);

    int N = in.nextInt();
    for (int i = 0; i < N; i++) {
      String P = in.next();
      Long D = in.nextLong();
      Island newIsland = new Island(P, D);
      listIsland.put(P, newIsland);
      for (int j = 0; j < D; j++) {
        Long A = in.nextLong();
        newIsland.addLand(A);
      }
      // Print the island for debug
      // out.println(newIsland.printIsland());
      // newIsland.printTreePreorder(newIsland.first.height);
      // out.println("");
    }

    String R = in.next();
    int E = in.nextInt();
    Island islandStart = listIsland.get(R);
    raiden.positionIsland = islandStart;
    raiden.positionLand = islandStart.startPosition(E);
    // out.println(raiden); // Print the shogun position for debug

    int Q = in.nextInt();
    for (int i = 0; i < Q; i++) {
      String command = in.next();
      if (command.equals("UNIFIKASI")) {
        String UInput = in.next();
        String VInput = in.next();
        Island U = listIsland.get(UInput);
        Island V = listIsland.get(VInput);
        U.handleUnifikasi(V);
      } else if (command.equals("PISAH")) {
        String UInput = in.next();
        Land U = listShrine.get(UInput);
        Island island = listIsland.get(U.nameShrine);
        island.handlePisah();
      } else if (command.equals("RISE")) {
        String UInput = in.next();
        Long H = in.nextLong();
        Long X = in.nextLong();
        Island U = listIsland.get(UInput);
        U.handleRise(H, X);
      } else if (command.equals("QUAKE")) {
        String UInput = in.next();
        Long H = in.nextLong();
        Long X = in.nextLong();
        Island U = listIsland.get(UInput);
        U.handleQuake(H, X);
      } else if (command.equals("SWEEPING")) {
        String UInput = in.next();
        Long L = in.nextLong();
        Island U = listIsland.get(UInput);
        U.handleSweeping(L);
      } else if (command.equals("TELEPORTASI")) {
        String VInput = in.next();
        Land V = listShrine.get(VInput);
        raiden.positionLand = V;
        raiden.positionIsland = V.island;
        out.println(raiden.positionLand.height);
      } else if (command.equals("GERAK")) {
        String direction = in.next();
        Long S = in.nextLong();
        raiden.positionIsland.handleGerak(direction, S);
      } else if (command.equals("STABILIZE")) {
        raiden.positionIsland.handleStabilize();
      } else if (command.equals("CRUMBLE")) {
        raiden.positionIsland.handleCrumble();
      } else if (command.equals("TEBAS")) {
        String direction = in.next();
        Long S = in.nextLong();
        raiden.positionIsland.handleTebas(direction, S);
      }
      // out.println(raiden);
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

    public Long nextLong() {
      return Long.parseLong(next());
    }

  }
}