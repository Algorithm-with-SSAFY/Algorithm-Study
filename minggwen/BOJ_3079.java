import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3079 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long  M = Integer.parseInt(st.nextToken());
		long arr[] = new long[N];
		for(int n=0;n<N;n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		long left = 1;
		long right = (long)arr[N-1]*M;
		long answer = right;
		while(left<=right) {
			long sum = 0;
			long mid = (left+right)/2;
			for(int n=0;n<N;n++) {
				sum+=mid/arr[n];
				if(sum>M) break;
			}
			if(sum>=M) {
				answer=Math.min(answer, mid);
				right=mid-1;
			}else {
				left = mid+1;
			}
		}
		System.out.println(answer);
	}

}
