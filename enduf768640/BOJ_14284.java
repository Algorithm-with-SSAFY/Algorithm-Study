import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14284 {

    private static final int INF = 10_000_001;

    private static int N, M, S, T;
    private static List<Edge>[] graph;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        dijkstra();

        printAnswer();
    }

    private static void dijkstra() {
        int[] distances = new int[N + 1];
        Arrays.fill(distances, INF);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        pq.offer(new Edge(S, 0));
        distances[S] = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (distances[current.getTo()] < current.getWeight()) {
                continue;
            }

            for (Edge next : graph[current.getTo()]) {
                if (distances[next.getTo()] > distances[current.getTo()] + next.getWeight()) {
                    distances[next.getTo()] = distances[current.getTo()] + next.getWeight();
                    pq.offer(new Edge(next.getTo(), distances[next.getTo()]));
                }
            }
        }

        answer = distances[T];
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private static class Edge {

        private int to;
        private int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }
    }
}
