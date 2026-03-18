import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  static StringBuilder sb = new StringBuilder();
  static int n;

  public static void main(String[] args) throws IOException {
    setUp();

    List<Integer> primeList = getPrimeArray(n);
    int[] prefixSum = new int[primeList.size() + 1];
    for (int i = 1; i < prefixSum.length; i++) {
      prefixSum[i] = prefixSum[i - 1] + primeList.get(i - 1);
    }

    int left = 0;
    int right = 1;
    int answer = 0;

    while (right < prefixSum.length) {
      int currSum = prefixSum[right] - prefixSum[left];

      if (currSum == n) {
        answer++;
        right++;
      } else if (currSum < n) {
        right++;
      } else {
        left++;
      }
    }

    sb.append(answer);

    output();
  }

  private static List<Integer> getPrimeArray(int n) {
    boolean[] sieve = new boolean[n + 1];
    Arrays.fill(sieve, true);

    for (int i = 2; i * i < n + 1; i++) {
      if (sieve[i]) {
        for (int j = i * i; j < n + 1; j += i) {
          sieve[j] = false;
        }
      }
    }

    List<Integer> result = new ArrayList<>();
    for (int i = 2; i < n + 1; i++) {
      if (sieve[i]) {
        result.add(i);
      }
    }

    return result;
  }

  private static void setUp() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
  }

  private static void output() throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(sb.toString());
    bw.flush();
    bw.close();
  }

}