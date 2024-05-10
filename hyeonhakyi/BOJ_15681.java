package ex0509;

import java.io.*;
import java.util.*;

public class BOJ_15681 {
    static ArrayList<ArrayList<Integer>> tree;
    static int[] dp;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        tree = new ArrayList<>();
        for (int i = 1; i <= N + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        dp = new int[N + 1];
        visit = new boolean[N + 1];
        visit[R] = true;
        count(R);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine());
            sb.append(dp[u]).append("\n");
        }

        System.out.println(sb);
    }

    private static int count(int n) {
        int total = 1;
        for (int next : tree.get(n)) {
            if (!visit[next]) {
                visit[next] = true;
                total += count(next);
            }
        }

        return dp[n] = total;
    }

}