import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사이클 게임 / 30분
public class BOJ_20040 {
	static StringBuilder sb = new StringBuilder();
	static int N, M, parents[];
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N];
		
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		int from, to, cnt = 1;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			if (union(from, to)) {
				sb.append(cnt);
				return;
			}
			cnt++;
		}
		sb.append(0);
	}

	static int find(int a) {
		if (parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int pa = find(a), pb = find(b);
		if (pa == pb) return true;
		parents[pa] = pb;
		
		return false;
	}
}
