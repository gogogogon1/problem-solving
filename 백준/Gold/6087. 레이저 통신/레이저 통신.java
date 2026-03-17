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
  static int n, m;
  static char[][] board;
  static int startY, startX;
  static int endY, endX;
  static int[] dy = {0, 1, 0, -1};
  static int[] dx = {1, 0, -1, 0};

  public static void main(String[] args) throws IOException {
    setUp();

    setStartAndEnd();
    sb.append(bfs());

    output();
  }

  private static int bfs() {
    int[][] dist = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        dist[i][j] = -1;
      }
    }

    Deque<Node> que = new ArrayDeque<>();
    que.offerLast(new Node(startY, startX));
    dist[startY][startX] = 0;

    while (!que.isEmpty()) {
      Node cur = que.pollFirst();

      for (int i = 0; i < 4; i++) {
        int ny = cur.y + dy[i];
        int nx = cur.x + dx[i];

        while (check(ny, nx)) {
          if (dist[ny][nx] == -1) {
            dist[ny][nx] = dist[cur.y][cur.x] + 1;
            que.offerLast(new Node(ny, nx));
          }
          if (ny == endY && nx == endX) {
            return dist[ny][nx] - 1;
          }
          ny += dy[i];
          nx += dx[i];
        }
      }
    }

    return -1;
  }

  private static boolean check(int y, int x) {
    return 0 <= y && y < n && 0 <= x && x < m && board[y][x] == '.';
  }

  private static void setStartAndEnd() {
    startY = -1;
    startX = -1;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i][j] != 'C') {
          continue;
        }

        board[i][j] = '.';
        if (startY == -1 && startX == -1) {
          startY = i;
          startX = j;
        } else {
          endY = i;
          endX = j;
        }
      }
    }
  }

  private static void setUp() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    m = Integer.parseInt(st.nextToken());
    n = Integer.parseInt(st.nextToken());
    board = new char[n][m];
    for (int i = 0; i < n; i++) {
      String row = br.readLine();
      for (int j = 0; j < m; j++) {
        board[i][j] = row.charAt(j);
      }
    }
  }

  private static void output() throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(sb.toString());
    bw.flush();
    bw.close();
  }

  private static class Node {

    int y, x;

    public Node(int y, int x) {
      this.y = y;
      this.x = x;
    }
  }

}