import java.io.*;
import java.util.*;

public class BOJ_2178 {

    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void helper(int x, int y) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{x, y});
        visited[x][y] = true;

        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            int cx = cur[0];
            int cy = cur[1];
            for (int i = 0; i < dx.length; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx < 0 || nx > arr.length - 1 || ny < 0 || ny > arr[0].length - 1 || visited[nx][ny] || arr[nx][ny] == 0) {
                    continue;
                }
                deque.add(new int[]{nx, ny});
                arr[nx][ny] = arr[cx][cy] + 1;
                visited[nx][ny] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                arr[i][j] = ch[j] - '0';
            }
        }

        helper(0, 0);
        bw.write(String.valueOf(arr[N - 1][M - 1]));

        br.close();
        bw.flush();
        bw.close();
    }
}
