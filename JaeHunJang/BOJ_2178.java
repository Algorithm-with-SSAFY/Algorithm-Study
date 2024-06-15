import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 미로 탐색 / 60분
public class BOJ_2178 {
    static StringBuilder sb = new StringBuilder();
    static int N, M, map[][], H, W, deltas[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean visited[][];

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            int j = 0;
            for (String ch : br.readLine().split("")) {
                map[i][j++] = Integer.parseInt(ch);
            }
        }

        bfs();
        System.out.println(sb.toString());
    }

    static void bfs() {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0));
        visited[0][0] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            cnt++;
            for (int s = 0; s < size; s++) {
                Pos cur = q.poll();

                if (cur.r == N-1 && cur.c == M-1) {
                    sb.append(cnt);
                    return;
                }

                for (int d = 0; d < deltas.length; d++) {
                    int nr = cur.r + deltas[d][0];
                    int nc = cur.c + deltas[d][1];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (visited[nr][nc] || map[nr][nc] == 0) continue;

                    visited[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                }
            }
        }
    }
}
