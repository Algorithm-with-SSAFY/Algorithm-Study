import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16973 {
	static int N,M,H,W,map[][];
	static int startR,startC,endR,endC;
	static int delta[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int n=0;n<N;n++) {
			st=new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m]=Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		startR = Integer.parseInt(st.nextToken())-1;
		startC = Integer.parseInt(st.nextToken())-1;
		endR = Integer.parseInt(st.nextToken())-1;
		endC = Integer.parseInt(st.nextToken())-1;
		System.out.println(bfs());
		
	}
	public static int bfs() {
		boolean visited[][] = new boolean[N][M];
		Queue<int[]> que = new ArrayDeque<int[]>();
		que.add(new int[] {startR,startC});
		visited[startR][startC] = true;
		int cnt = 0;
		boolean flag = false;
		while(!que.isEmpty()) {
			int size = que.size();
			for(int s=0;s<size;s++) {
				int r = que.peek()[0];
				int c = que.peek()[1];
				que.poll();
				if(r==endR&&c==endC) {
					flag = true;
					break;
				}
				for(int d=0;d<4;d++) {
					int rd = r+delta[d][0];
					int cd = c+delta[d][1];
					if(isIn(rd,cd)&&!visited[rd][cd]&&canMove(rd,cd)) {
						visited[rd][cd] = true;
						que.add(new int[] {rd,cd});
					}
				}
			}
			if(flag) break;
			cnt++;
		}
		return flag?cnt:-1;
	}
	public static boolean isIn(int row,int col) {
		return 0<=row&&row<N&&0<=col&&col<M&row+H-1<N&&col+W-1<M?true:false;
	}
	public static boolean canMove(int row,int col) {
		for(int r=row;r<row+H;r++) {
			for(int c=col;c<col+W;c++) {
				if(map[r][c]==1) {
					return false;
				}
			}
		}
		return true;
	}
}
