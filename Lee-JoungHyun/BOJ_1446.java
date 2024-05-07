import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1446 {

    private static class ShortPath implements Comparable<ShortPath> {
        int start, end, cost;

        public ShortPath (int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(ShortPath o) {
            if (this.end != o.end)
                return this.end - o.end;
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        PriorityQueue<ShortPath> shortPaths = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            shortPaths.add(new ShortPath(start, end, cost));
        }


        int[] cost = new int[10001];

        for (int i = 0; i < D + 1; i++) {
            cost[i] = i;
        }


        for (int i = 0; i < N; i++) {
            ShortPath sp = shortPaths.poll();
            if (cost[sp.end] > cost[sp.start] + sp.cost) {
                cost[sp.end] = cost[sp.start] + sp.cost;
                setting(cost, sp.end, cost[sp.end], D);
            }
        }
        //System.out.println(Arrays.toString(cost));
        System.out.println(cost[D]);
    }
    private static void setting(int[] cost, int poz, int value, int D) {
        //System.out.println(poz + " " + value);
        if (poz <= D) {
            for (int i = 0; i < D - poz + 1; i++) {
                cost[poz + i] = Math.min(value + i, cost[poz + i]);
                //System.out.println((poz + i) + " " + cost[poz + i]);
            }
        }
        else {
            for (int i = 0; i < poz - D + 1; i++) {
                cost[poz - i] = Math.min(value + i, cost[poz - i]);
            }
        }
    }
}