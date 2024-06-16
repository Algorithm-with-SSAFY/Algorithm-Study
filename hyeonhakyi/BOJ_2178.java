package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178 {
    private static class Node{
        int x;
        int y;
        int count;
        public Node(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    private static int n,m,min = Integer.MAX_VALUE;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int i = 0; i < n; i++){
            String s = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = s.charAt(j) - '0';
            }
        }

        bfs();
        System.out.println(min);
    }//main end

    private static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0));
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.x == n-1 && now.y == m-1){
                min = Math.min(now.count+1,min);
                return;
            }

            for(int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(!check(nx,ny))continue;
                if(visited[nx][ny])continue;
                if(map[nx][ny] == 0)continue;

                visited[nx][ny] = true;
                q.offer(new Node(nx, ny, now.count + 1));
            }
        }
    }//bfs end

    private static boolean check(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}//class end
