package SDA.Lab2;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class Lab2 {

    private static InputReader in;
    private static PrintWriter out;

    // TODO
    private static ArrayDeque<String> queueName = new ArrayDeque<String>();
    private static ArrayDeque<Integer> queueNumber = new ArrayDeque<Integer>();
    private static Integer queueSize = 0;
    private static HashMap<String, Integer> map = new HashMap<String, Integer>();

    static private int handleDatang(String Gi, int Xi) {
        queueName.add(Gi);
        queueNumber.add(Xi);
        queueSize += Xi;
        return queueSize;
    }

    // TODO
    static private String handleLayani(int Yi) {
        if (queueNumber.peek() >= Yi) {
            if (map.containsKey(queueName.peek())) {
                map.put(queueName.peek(), map.get(queueName.peek()) + Yi);
            } else {
                map.put(queueName.peek(), Yi);
            }
            int k = queueNumber.peek();
            if (k == Yi) {
                queueName.remove();
                queueNumber.remove();
            } else {
                k -= Yi;
                queueNumber.remove();
                queueNumber.addFirst(k);
            }
        } else {
            int j = Yi;
            while (j > queueNumber.peek()) {
                if (map.containsKey(queueName.peek())) {
                    map.put(queueName.peek(), map.get(queueName.peek()) + queueNumber.peek());
                } else {
                    map.put(queueName.peek(), queueNumber.peek());
                }
                j -= queueNumber.peek();
                queueName.remove();
                queueNumber.remove();
            }
            if (map.containsKey(queueName.peek())) {
                map.put(queueName.peek(), map.get(queueName.peek()) + j);
            } else {
                map.put(queueName.peek(), j);
            }
            int k = queueNumber.peek();
            if (k != 0) {
                k -= j;
                queueNumber.remove();
                queueNumber.addFirst(k);
            } else {
                queueName.remove();
                queueNumber.remove();
            }
        }
        queueSize -= Yi;
        return queueName.peek();
    }

    // TODO
    static private int handleTotal(String Gi) {
        try {
            return map.get(Gi);
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public static void main(String args[]) throws IOException {

        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        int N;

        N = in.nextInt();

        for (int tmp = 0; tmp < N; tmp++) {
            String event = in.next();

            if (event.equals("DATANG")) {
                String Gi = in.next();
                int Xi = in.nextInt();

                out.println(handleDatang(Gi, Xi));
            } else if (event.equals("LAYANI")) {
                int Yi = in.nextInt();

                out.println(handleLayani(Yi));
            } else {
                String Gi = in.next();

                out.println(handleTotal(Gi));
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