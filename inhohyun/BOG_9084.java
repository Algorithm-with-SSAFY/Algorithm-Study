import java.io.*;
import java.util.*;
 
public class BOG_9084 {
 
    private static int T, N, M;
    private static int[] arr, cache;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            M = Integer.parseInt(br.readLine());
 
            cache = new int[M + 1];
            cache[0] = 1;
            for (int coin : arr) {
                for (int j = coin; j <= M; j++) {
                    cache[j] += cache[j - coin];
                }
            }
 
            sb.append(cache[M]).append("\n");
        }
 
        System.out.println(sb.toString());
    }
}
