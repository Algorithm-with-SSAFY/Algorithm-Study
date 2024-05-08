import java.io.*;
import java.util.*;

public class BOJ_14284 {
    static class Edge implements Comparable<Edge> {
        int target, cost;

        public Edge(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static final int INF = 100_000_000;
    static int N, M, start, end;
    static List<Edge>[] edges;
    static int[] distance;
    static PriorityQueue<Edge> pq;

    static void helper() {
        pq.add(new Edge(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (distance[cur.target] < cur.cost) {
                continue;
            }

            for (Edge next : edges[cur.target]) {
                int weightSum = cur.cost + next.cost;
                if (weightSum < distance[next.target]) {
                    distance[next.target] = weightSum;
                    pq.add(new Edge(next.target, distance[next.target]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N + 1];
        distance = new int[N + 1];
        pq = new PriorityQueue<>();
        Arrays.fill(distance, INF);
        for (int i = 1; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, c));
        }

        st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        helper();

        bw.write(String.valueOf(distance[end]));
        br.close();
        bw.close();
    }

}