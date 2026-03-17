import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

  static StringBuilder sb = new StringBuilder();
  static int n, m;
  static int[][] board;
  static int[] parents;

  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};

  public static void main(String[] args) throws IOException {
    setUp();

    parents = new int[n * m];
    for (int i = 0; i < n * m; i++) {
      parents[i] = i;
    }

    for (int i = 0; i < n * m; i++) {
      int r = i / m;
      int c = i % m;
      int nr = r + dy[board[r][c]];
      int nc = c + dx[board[r][c]];
      union(nr * m + nc, i);
    }

    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < n * m; i++) {
      set.add(find(i));
    }

    sb.append(set.size());
    output();
  }

  private static void union(int parent, int child) {
    parent = find(parent);
    child = find(child);

    parents[child] = parent;
  }

  private static int find(int x) {
    if (parents[x] != x) {
      parents[x] = find(parents[x]);
    }
    return parents[x];
  }

  private static void setUp() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    board = new int[n][m];
    for (int i = 0; i < n; i++) {
      String row = br.readLine();
      for (int j = 0; j < m; j++) {
        char cur = row.charAt(j);
        if (cur == 'U') {
          board[i][j] = 3;
        } else if (cur == 'D') {
          board[i][j] = 1;
        } else if (cur == 'L') {
          board[i][j] = 2;
        } else if (cur == 'R') {
          board[i][j] = 0;
        }
      }
    }
  }

  private static void output() throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(sb.toString());
    bw.flush();
    bw.close();
  }

}