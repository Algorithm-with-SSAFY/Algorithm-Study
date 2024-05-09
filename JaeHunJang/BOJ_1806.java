import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분합 / 60분
public class BOJ_1806 {
	static StringBuilder sb = new StringBuilder();
	static int N, S, list[];
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		list = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) list[i] = Integer.parseInt(st.nextToken());
		
		solve();
	}

	 static void solve() {
		 int sum = list[0];
		 int len = N+1;
		 int start = 0, end = 0;
		 
		 while (start <= end) {			 
//			 System.out.println(start + "|" + end + "|" + sum);
			 if (sum < S) {
				 end++;
				 if (end >= N) break;
				 sum += list[end];
			 } else {
				 len = Math.min(len, end - start + 1);
				 sum -= list[start];
				 start++;
			 }
		 }
		 if (len == N+1) len = 0;
		 
		 sb.append(len);
	}
	
}
