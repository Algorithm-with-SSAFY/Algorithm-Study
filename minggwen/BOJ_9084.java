import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9084 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =null;
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			int N = Integer.parseInt(br.readLine());
			st=new StringTokenizer(br.readLine());
			int coins[] = new int[N];
			for(int n=0;n<N;n++) {
				coins[n] = Integer.parseInt(st.nextToken());
			}
			
			int M = Integer.parseInt(br.readLine());
			int dp[] = new int[M+1];
	
			for(int coin:coins) {
				for(int i=coin;i<=M;i++) {
					if(i-coin==0) {
						dp[i]++;
					}else {
						dp[i]+=dp[i-coin];
					}
					
				}
			}
			System.out.println(dp[M]);
			
		}
	}
}
