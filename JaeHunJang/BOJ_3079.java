import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 입국심사 / 120분
public class BOJ_3079 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        // 가장 오래 걸리는 시간을 구함
        int max = 0;
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }
      
        long left = 0L;
        long right = max * 1000000000L;

        long ans = 0L;
        while (left <= right){
            long mid = (left + right) / 2;
            // mid초 일 때 각 입국 심사대에서 보낼 수 있는 수를 카운트
            long cnt = 0;
            for(int i = 0; i < N; i++){
                cnt += (mid / arr[i]);
            }

            // 비교 후 탐색 범위 변경
            if(cnt >= M){
                ans = mid;
                right = mid-1;
            }  else if (cnt < M){
                left = mid +1;
            }
        }

        System.out.println(ans);
    }
}