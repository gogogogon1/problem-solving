import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {

  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    PriorityQueue<Integer> right = new PriorityQueue<>();

    for (int i = 0; i < n; i++) {
      int curr = Integer.parseInt(br.readLine());

      if (left.size() == right.size()) {
        left.offer(curr);
      } else {
        right.offer(curr);
      }

      if (!left.isEmpty() && !right.isEmpty() && right.peek() < left.peek()) {
        int leftValue = left.poll();
        int rightValue = right.poll();

        left.offer(rightValue);
        right.offer(leftValue);
      }

      sb.append(left.peek()).append("\n");
    }

    output();
  }

  private static void output() throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(sb.toString());
    bw.flush();
    bw.close();
  }

}