import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] delta = {{1,0},{0,-1},{-1,0},{0,1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for(int i = 0 ; i<N ; i++) {
			String str = br.readLine();
			for(int j = 0 ; j<M ; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0});
		int[][] visit = new int[N][M];
		visit[0][0] = 1;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int d = 0 ; d<4 ;d++) {
				int nr = cur[0]+delta[d][0];
				int nc = cur[1]+delta[d][1];
				if(nr>=0 && nr<N && nc>=0 && nc<M && map[nr][nc]==1 && visit[nr][nc]==0) {
					q.add(new int[] {nr,nc});
					visit[nr][nc] = visit[cur[0]][cur[1]]+1;
				}
			}
		}
		System.out.println(visit[N-1][M-1]);
		
	
	}

}
