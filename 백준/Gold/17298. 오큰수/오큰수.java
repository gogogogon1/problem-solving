import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

  static StringBuilder sb = new StringBuilder();
  static int n;
  static int[] numbers;

  public static void main(String[] args) throws IOException {
    setUp();

    Stack<Integer> stack = new Stack<>();
    int[] answer = new int[n];
    for (int i = n - 1; i > -1; i--) {
      int cur = numbers[i];
      while (!stack.isEmpty() && cur >= stack.peek()) {
        stack.pop();
      }

      if (stack.isEmpty()) {
        answer[i] = -1;
      } else {
        answer[i] = stack.peek();
      }

      stack.push(cur);
    }

    for (int cur : answer) {
      sb.append(cur).append(" ");
    }

    output();
  }

  private static void setUp() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    n = Integer.parseInt(br.readLine());
    numbers = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      numbers[i] = Integer.parseInt(st.nextToken());
    }
  }

  private static void output() throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(sb.toString());
    bw.flush();
    bw.close();
  }

}

/*
7
3 2 1 2 3 4 5
 */