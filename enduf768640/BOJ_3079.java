import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3079 {

    private static int N, M;
    private static int[] times;

    private static long answer = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        binarySearch();

        printAnswer();
    }

    private static void binarySearch() {
        long start = 0;
        long end = (long) times[N - 1] * M;

        while (start <= end) {
            long mid = (start + end) / 2;

            long sum = 0;
            for (int time : times) {
                long count = mid / time;

                sum += count;

                if (sum >= M) {
                    break;
                }
            }

            if (sum >= M) {
                answer = Math.min(answer, mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        times = new int[N];
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(times);
    }

    private static void printAnswer() {
        System.out.println(answer);
    }
}
