import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1446 {
	static class Road implements Comparable<Road>{
		int start, end, km;

		public Road(int start, int end, int km) {
			this.start = start;
			this.end = end;
			this.km = km;
		}

		@Override
		public int compareTo(Road o) {
			if(this.start==o.start) return this.end-o.end;
			return this.start-o.start;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		Queue<Road> roads = new PriorityQueue<>();
		for(int n=0;n<N;n++) {
			st=new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			if(end>D||end-start<=m)continue;
			roads.add(new Road(start,end,m));
		}
		int dp[] = new int[D+1];
		for(int d=0;d<=D;d++) {
			dp[d] = d;
		}
		int move =0;
		while(move<D) {
			Road r = roads.peek();
			if(!roads.isEmpty()) {
				if(move==r.start) {
					dp[r.end]=Math.min(dp[move]+r.km,dp[r.end]);
					roads.poll();
				}else {
					dp[move+1]=Math.min(dp[move+1],dp[move]+1);
					move++;
				}
			}else {
				dp[move+1]=Math.min(dp[move+1],dp[move]+1);
				move++;
			}
			
		}
		System.out.println(dp[D]);
	}
}
