package ex0509;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20040 {
    private static int[] arr;
    private static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = i;
        }

        int result = 0;
        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(!union(x,y)){
                result = i;
                break;
            }
        }
        System.out.println(result);
    }//main end

    private static int find(int x){
        if(x == arr[x]){
            return x;
        }
        return arr[x] = find(arr[x]);
    }//find end

    private static boolean union(int x,int y){
        int x_arr = find(x);
        int y_arr = find(y);

        if(x_arr == y_arr){
            return false;
        }
        arr[x_arr] = y_arr;
        return true;
    }
}//class end
