import java.io.*;
import java.util.*;

public class BOJ_1806 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start, end, sum, answer;
        start = end = sum = 0;
        answer = Integer.MAX_VALUE;

        while (start <= end && end <= N) {
            if (sum >= S) {
                answer = Math.min(answer, end - start);
            }
            if (sum >= S) {
                sum -= arr[start++];
            } else {
                sum += arr[end++];
            }
        }
        bw.write(String.valueOf(answer == Integer.MAX_VALUE ? 0 : answer));

        br.close();
        bw.close();
    }
}
