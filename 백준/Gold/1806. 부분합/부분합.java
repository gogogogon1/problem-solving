import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

  static StringBuilder sb = new StringBuilder();
  static int n, s;
  static int[] prefixSum;

  public static void main(String[] args) throws IOException {
    setUp();

    int left = 0;
    int right = 1;
    int answer = Integer.MAX_VALUE;

    while (right < n + 1) {
      int sum = prefixSum[right] - prefixSum[left];

      if (s <= sum) {
        answer = Math.min(answer, right - left);
        left++;
      } else {
        right++;
      }
    }

    if (answer == Integer.MAX_VALUE) {
      sb.append(0);
    } else {
      sb.append(answer);
    }

    output();
  }

  private static void setUp() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    s = Integer.parseInt(st.nextToken());
    prefixSum = new int[n + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < n + 1; i++) {
      prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
    }
  }

  private static void output() throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(sb.toString());
    bw.flush();
    bw.close();
  }

}