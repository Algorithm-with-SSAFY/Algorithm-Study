import java.io.*;
import java.util.*;

public class BOJ_16973 {
    static class Point {
        int x, y, cost;

        public Point(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    static int N, M, H, W, sx, sy, fx, fy, answer;
    static int[][] map;
    static boolean[][] visited;
    static Deque<Point> deque;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};

    static void helper() {
        deque.add(new Point(sx, sy, 0));
        visited[sx][sy] = true;

        while (!deque.isEmpty()) {
            Point cur = deque.poll();
            int x = cur.x;
            int y = cur.y;
            int c = cur.cost;
            if (x == fx && y == fy) {
                answer = c;
                return;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 1 || nx + H - 1 > N || ny < 1 || ny + W - 1 > M || visited[nx][ny] || map[nx][ny] == 1) {
                    continue;
                }

                if (isPossible(nx, ny)) {
                    visited[nx][ny] = true;
                    deque.add(new Point(nx, ny, c + 1));
                }
            }
        }
    }

    static boolean isPossible(int x, int y) {
        for (int i = x; i < x + H; i++) {
            for (int j = y; j < y + W; j++) {
                if (map[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = -1;
        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        fx = Integer.parseInt(st.nextToken());
        fy = Integer.parseInt(st.nextToken());

        helper();
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}