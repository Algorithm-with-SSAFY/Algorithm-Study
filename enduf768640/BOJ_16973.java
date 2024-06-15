import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16973 {

    private static int N, M;
    private static boolean[][] map;

    private static int H, W;
    private static Location start, end;

    private static int answer;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        init();

        bfs();

        printAnswer();
    }

    private static void bfs() {
        Queue<Location> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][M + 1];
        int[][] count = new int[N + 1][M + 1];

        queue.offer(start);
        visited[start.getY()][start.getX()] = true;

        while (!queue.isEmpty()) {
            Location location = queue.poll();

            for (int i = 0; i < 4; i++) {
                int Y = location.getY() + dy[i];
                int X = location.getX() + dx[i];

                if (!check(Y, X) || visited[Y][X]) {
                    continue;
                }

                queue.offer(new Location(Y, X));
                visited[Y][X] = true;
                count[Y][X] = count[location.getY()][location.getX()] + 1;
            }
        }

        answer = count[end.getY()][end.getX()] == 0 ? -1 : count[end.getY()][end.getX()];
    }

    private static boolean check(int Y, int X) {
        for (int y = Y; y < Y + H; y++) {
            if (y < 1 || y > N || X < 1 || X > M || !map[y][X]) {
                return false;
            }

            if (y < 1 || y > N || X + W - 1 < 1 || X + W - 1 > M || !map[y][X + W - 1]) {
                return false;
            }
        }

        for (int x = X; x < X + W; x++) {
            if (Y < 1 || Y > N || x < 1 || x > M || !map[Y][x]) {
                return false;
            }

            if (Y + H - 1 < 1 || Y + H - 1 > N || x < 1 || x > M || !map[Y + H - 1][x]) {
                return false;
            }
        }

        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N + 1][M + 1];
        for (int y = 1; y <= N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= M; x++) {
                map[y][x] = st.nextToken().equals("0");
            }
        }

        st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        start = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        end = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private static class Location {

        private int y;
        private int x;

        public Location(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }
}
