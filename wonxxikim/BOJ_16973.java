import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] delta = {{1,0},{0,1},{-1,0},{0,-1}};
	static int H,W;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map  = new int[N+1][M+1];
		int[][] visit = new int[N+1][M+1];
		for(int i = 1 ; i<=N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j<=M ; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		int Sr = Integer.parseInt(st.nextToken());
		int Sc = Integer.parseInt(st.nextToken());
		int Fr = Integer.parseInt(st.nextToken());
		int Fc = Integer.parseInt(st.nextToken());
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {Sr,Sc});
		visit[Sr][Sc] = 1;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int d = 0 ;d<4 ;d++) {
				int nr = cur[0]+delta[d][0];
				int nc = cur[1] +delta[d][1];
				if(nr>=1 && nr<=N-H+1 && nc>=1 && nc<=M-W+1 && visit[nr][nc]==0 && check(nr,nc,map)) {
					q.add(new int[] {nr,nc});
					visit[nr][nc] = visit[cur[0]][cur[1]]+1;
				}
			}
		}
		if(visit[Fr][Fc]==0) System.out.println(-1);
		else System.out.println(visit[Fr][Fc]-1);
	}
	static boolean check(int nr, int nc, int[][] map) {
		for(int i = nr ; i<nr+H ; i++) {
			for(int j = nc ; j<nc+W ;j++) {
				if(map[i][j] == 1) return false;
			}
		}
	
		return true;
	}
}
