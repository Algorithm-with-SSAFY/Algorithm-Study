import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 직사각형 탈출 / 60분
public class BOJ_16973 {
    static StringBuilder sb = new StringBuilder();
    static int N, M, map[][], H, W, deltas[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static Pos start, end;
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

        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        end = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        bfs();
        System.out.println(sb.toString());
    }

    static void bfs() {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.r][start.c] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            cnt++;
            for (int s = 0; s < size; s++) {
                Pos cur = q.poll();

                if (cur.r == end.r && cur.c == end.c) {
                    sb.append(cnt-1);
                    return;
                }

                for (int d = 0; d < deltas.length; d++) {
                    int nr = cur.r + deltas[d][0];
                    int nc = cur.c + deltas[d][1];

                    if (nr < 1 || nr >= N || nc < 1 || nc >= M) continue;
                    if (visited[nr][nc]) continue;
                    if (!isValid(nr, nc)) continue;

                    visited[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                }

            }
        }

        sb.append(-1);
    }

    static boolean isValid(int r, int c) {
        for (int i = r; i < r + H; i++) {
            for (int j = c; j < c + W; j++) {
                if (i > N || j > M) return false;
                if (map[i][j] == 1) return false;
            }
        }

        return true;
    }
}
