package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3079 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        int height = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            height = Math.max(height, arr[i]);
        }

        long result = height * m;

        Arrays.sort(arr);

        long min = 0;
        long max = height * m;

        while(min <= max){
            long mid = (max + min) / 2;

            long totalCount = 0;
            for(int i : arr){
                long count = mid / i;

                if(totalCount >= m || i > mid){
                    break;
                }

                totalCount += count;
            }

            if(totalCount >= m){
                max = mid - 1;
                result = Math.min(result, mid);
            }else{
                min = mid + 1;
            }
        }
        System.out.println(result);
    }//main end
}//class end
