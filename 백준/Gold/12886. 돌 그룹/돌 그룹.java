import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

  static StringBuilder sb = new StringBuilder();
  static int a, b, c, total;
  static boolean[][] visited;

  public static void main(String[] args) throws IOException {
    setUp();

    total = a + b + c;
    if (total % 3 != 0) {
      sb.append(0);
    } else {
      sb.append(solve());
    }

    output();
  }

  private static int solve() {
    Deque<Node> que = new ArrayDeque<>();
    visited = new boolean[total + 1][total + 1];

    que.offerLast(new Node(a, b));
    visited[a][b] = true;

    while (!que.isEmpty()) {
      Node cur = que.pollFirst();

      int[] arr = {cur.num1, cur.num2, total - cur.num1 - cur.num2};

      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (i != j && arr[i] < arr[j]) {
            int[] tmp = {cur.num1, cur.num2, total - cur.num1 - cur.num2};
            tmp[i] += arr[i];
            tmp[j] -= arr[i];
            if (!visited[tmp[0]][tmp[1]]) {
              visited[tmp[0]][tmp[1]] = true;
              que.offerLast(new Node(tmp[0], tmp[1]));
            }
          }
        }
      }
    }

    return visited[total / 3][total / 3] ? 1 : 0;
  }

  private static void setUp() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    a = Integer.parseInt(st.nextToken());
    b = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
  }

  private static void output() throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(sb.toString());
    bw.flush();
    bw.close();
  }

  private static class Node {

    int num1, num2;

    public Node(int num1, int num2) {
      this.num1 = num1;
      this.num2 = num2;
    }
  }

}