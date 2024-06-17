import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178 {

    private static int N, M;
    private static boolean[][] map;

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
        boolean[][] visited = new boolean[N][M];
        int[][] count = new int[N][M];

        queue.offer(new Location(0, 0));
        visited[0][0] = true;
        count[0][0] = 1;

        while (!queue.isEmpty()) {
            Location location = queue.poll();

            for (int i = 0; i < 4; i++) {
                int Y = location.getY() + dy[i];
                int X = location.getX() + dx[i];

                if (Y < 0 || Y >= N || X < 0 || X >= M || visited[Y][X] || !map[Y][X]) {
                    continue;
                }

                queue.offer(new Location(Y, X));
                visited[Y][X] = true;
                count[Y][X] = count[location.getY()][location.getX()] + 1;
            }
        }

        answer = count[N - 1][M - 1];
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        for (int y = 0; y < N; y++) {
            String[] input = br.readLine().split("");
            for (int x = 0; x < M; x++) {
                map[y][x] = input[x].equals("1");
            }
        }
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
