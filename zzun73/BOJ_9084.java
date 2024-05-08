import java.io.*;
import java.util.*;

public class BOJ_9084 {

    static int T, N, M;
    static int[] coin, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            coin = new int[N];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine());
            dp = new int[M + 1];
            dp[0] = 1;

            for (int i = 0; i < N; i++) {
                for (int j = coin[i]; j < M + 1; j++) {
                    dp[j] = dp[j] + dp[j - coin[i]];
                }
            }
            sb.append(dp[M]).append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}