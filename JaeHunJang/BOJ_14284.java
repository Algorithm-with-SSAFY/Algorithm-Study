import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 간선 이어가기 2 / 60분
public class BOJ_14284 {
	static StringBuilder sb = new StringBuilder();
	static int n, m, s, t;
	static long weights[];
	static List<Node>[] list;
	static class Node {
		int to;
		long w;

		public Node(int to, long w) {
			this.to = to;
			this.w = w;
		}
	}
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		weights = new long[n+1];
		list = new List[n+1];
		
		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		
		int from, to, w;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			list[from].add(new Node(to, w));
			list[to].add(new Node(from, w));
		}
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		solve();
	}

	 static void solve() {
		 PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.w, o2.w));
		 Arrays.fill(weights, Long.MAX_VALUE);
		 weights[s] = 0;
		 boolean[] visited = new boolean[n+1];
		 pq.offer(new Node(s, 0));
		 
		 while(!pq.isEmpty()) {
			 Node cur = pq.poll();
			 
			 if (visited[cur.to]) continue;
			 visited[cur.to] = true;
			 
			 for(Node next : list[cur.to]) {
				 if (weights[next.to] > weights[cur.to] + next.w ) {
					 weights[next.to] = weights[cur.to] + next.w;
					 pq.offer(new Node(next.to, weights[next.to]));
				 }
			 }
		 }
		 
		 sb.append(weights[t]);
	}
	
}
