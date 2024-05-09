import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14284 {

	static class Edge{
		int start,end,len;

		public Edge(int start, int end, int len) {
			super();
			this.start = start;
			this.end = end;
			this.len = len;
		}

		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", len=" + len + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<List<Edge>> arr = new ArrayList<>();
		for(int n=0;n<=N;n++)arr.add(new ArrayList<>());
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr.get(a).add(new Edge(a,b,c));
			arr.get(b).add(new Edge(b,a,c));
		}
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int dist[] = new int[N+1];
		boolean visited[] = new boolean[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[S]=0;
		int min = Integer.MAX_VALUE;
		int idx= -1;
		for(int n=1;n<=N;n++) {
			min=Integer.MAX_VALUE;
			idx= -1;
			for(int i=1;i<=N;i++) {
				if(dist[i]<min&&!visited[i]){
					min=dist[i];
					idx=i;
				}
			}
			if(idx==-1)break;
			if(idx==T)break;
			visited[idx]=true;
			for(int i=0;i<arr.get(idx).size();i++) {
				Edge e = arr.get(idx).get(i);
				if(!visited[e.end]&&dist[e.end]>min+e.len) {
					dist[e.end]=min+e.len;
				}
			}
		}
		System.out.println(dist[T]);
	}

}
