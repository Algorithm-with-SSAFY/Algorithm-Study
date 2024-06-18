import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static boolean map[][];
	static int[][] delta = {{1,0},{0,1},{-1,0},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		for(int n=0;n<N;n++) {
			String str = br.readLine();
			for(int m=0;m<M;m++) {
				if(str.charAt(m)=='1')map[n][m] = true;
			}
		}
		System.out.println(bfs());
	}
	private static int bfs() {
		int cnt = 0;
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] {0,0});
		visited[0][0] = true;
		while(!que.isEmpty()) {
			int size = que.size();
			cnt++;
			for(int s=0;s<size;s++) {
				int r = que.peek()[0];
				int c = que.peek()[1];
				if(r==N-1&&c==M-1) return cnt;
				que.poll();
				for(int d=0;d<4;d++) {
					int nr = r+delta[d][0];
					int nc = c+delta[d][1];
					if(0<=nr&&nr<N&&0<=nc&&nc<M&&!visited[nr][nc]&&map[nr][nc]) {
						que.add(new int[] {nr,nc});
						visited[nr][nc] = true;
					}
				}
			}
		}
		return cnt;
	}
}
