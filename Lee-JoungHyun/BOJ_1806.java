import java.util.*;

public class BOJ_1806 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int S = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }
        int minLength = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (true) {
            if (sum >= S) {
                minLength = Math.min(minLength, end - start);
                sum -= arr[start++];
            } else if (end == N) {
                break;
            } else {
                sum += arr[end++];
            }
        }
        System.out.println(minLength == Integer.MAX_VALUE ? 0 : minLength);
    }
}
