import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14284 {
    static int n, m;
    static List<Node>[] arr;

    static class Node implements Comparable<Node> {
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }

        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from  = Integer.parseInt(st.nextToken());
            int to  = Integer.parseInt(st.nextToken());
            int cost  = Integer.parseInt(st.nextToken());
            arr[from].add(new Node(to, cost));
            arr[to].add(new Node(from, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        dijkstra(start, end);
    }

    private static void dijkstra(int start, int end) {
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            int idx = pq.poll().idx;
            if (visited[idx]) continue;
            visited[idx] = true;
            for (Node next : arr[idx]) {
                if (dist[next.idx] > dist[idx] + next.cost) {
                    dist[next.idx] = dist[idx] + next.cost;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        System.out.println(dist[end]);
    }
}
