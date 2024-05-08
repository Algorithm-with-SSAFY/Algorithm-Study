package ex0508;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1806 {
    private static int n,s;
    private static int[] num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        num = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int result = 100001;
        int sum = 0;
        while(true) {
            if(sum >= s){
                sum -= num[start];
                result = Math.min(result, end - start);
                start++;
            }else if(end == n){
                break;
            }else{
                sum += num[end++];
            }
        }
        if(result == 100001){
            System.out.println(0);
        }else{
            System.out.println(result);
        }
    }//main end
}//class end
