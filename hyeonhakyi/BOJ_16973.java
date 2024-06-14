package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16973 {
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
    private static int n,m;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static int h,w,stX,stY,endX,endY;
    private static int[][] map;
    private static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        stX = Integer.parseInt(st.nextToken());
        stY = Integer.parseInt(st.nextToken());
        endX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());

        bfs();
        if(min == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(min);
        }
    }//main end

    private static void bfs() {
        Queue<Node> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        que.offer(new Node(stX-1,stY-1,0));
        visited[stX-1][stY-1] = true;

        while(!que.isEmpty()){
            Node now = que.poll();

            if(now.x == endX-1 && now.y == endY-1){
                min = now.count;
                return;
            }

            for(int d = 0; d < 4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(!check(nx,ny))continue;
                if(visited[nx][ny])continue;
                if(!isValid(nx,ny))continue;

                visited[nx][ny] = true;
                que.offer(new Node(nx,ny,now.count+1));
            }
        }
    }//bfs end

    private static boolean isValid(int x, int y){
        for(int i = x; i < x + h; i++){
            for(int j = y; j < y + w; j++){
                if(i >= n || j >= m || i < 0 || j < 0 || map[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }//isValid end

    private static boolean check(int x,int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }//check end
}//class end
