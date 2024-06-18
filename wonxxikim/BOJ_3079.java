import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 심사대의 수
		long M = Integer.parseInt(st.nextToken()); //친구의 수
		
		int[] T = new int[N]; //심사 시간
		int time_max = 0; //심사 최대시간
		for(int i = 0 ; i<N ; i++) {
			T[i] = Integer.parseInt(br.readLine());
			time_max = Math.max(time_max, T[i]);
		}
		Arrays.sort(T); // 정렬 해줘야 함 !!
		
		long result = Long.MAX_VALUE; // 결과

		//이분탐색 시작
		long max = time_max*M;
		long min = 0;
			
		while(min<=max) {
			long mid = (max+min)/2;
			long cnt = 0 ; //입국 심사 가능한 친구의 수
			
			for(int t : T) {
				cnt+=mid/t; //입국심사 가능한 사람의 수 ++
				if(cnt>=M) break; // M보다 커지면 break
			}
			
			if(cnt>=M) {
				max = mid-1;
				result = Math.min(mid, result);
			}
			else {
				min = mid+1;
			}
		}
		
		System.out.println(result);
		
	}

}
