import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 1446. 지름길 / 40분
public class BOJ_1446 {
    static StringBuilder sb = new StringBuilder();
    static int N, D, minDist;
    static Edge[] list;

    static class Edge {
        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(sb.toString());
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        minDist = Integer.MAX_VALUE;

        list = new Edge[N];


        int from, to, w;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            list[i] = new Edge(from, to, w);
        }

        Arrays.sort(list, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.from == o2.from) {
                    if (o1.to == o2.to) return Integer.compare(o1.w, o2.w);
                    return Integer.compare(o1.to, o2.to);
                }
                return Integer.compare(o1.from, o2.from);
            }
        });

        solve();
    }

    static void solve() throws Exception {
        dfs(0, 0,0, 0);

        sb.append(minDist);
    }

    static void dfs(int cnt, int start, int pos, int d) {
        minDist = Math.min(minDist, d + (D - pos));
        if (cnt == N) {
            return;
        }

        for (int i = start; i < N; i++) {
            if (list[i].to > D || list[i].from < pos) continue;
            dfs(cnt+1, i+1, list[i].to, d + (list[i].from - pos + list[i].w));
        }
    }
}