import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 9084. 동전 / 60분
public class BOJ_9084 {
	static StringBuilder sb = new StringBuilder();
	static int N, coins[], M, dp[];
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			coins = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}

			M = Integer.parseInt(br.readLine());
			dp = new int[M+1];
			
			solve();
		}

	}
	
	static void solve() throws Exception {
		for (int i = 0; i < N; i++) {
			for (int j = 1; j <= M; j++) {
				if(j - coins[i] > 0) {
					dp[j] = dp[j] + dp[j - coins[i]];
				} else if (j - coins[i] == 0) {
					dp[j]++;
				}
			}
		}
		
		sb.append(dp[M]).append("\n");
	}
}