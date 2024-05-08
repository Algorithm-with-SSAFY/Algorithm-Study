import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806 {

    private static int N, S;
    private static int[] arr;

    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        twoPointer();

        printAnswer();
    }

    private static void twoPointer() {
        int start = 0;
        int end = 0;

        int sum = arr[0];

        while (!(end == arr.length - 1 && sum < S)) {
            if (sum >= S) {
                answer = Math.min(answer, end - start + 1);
                sum -= arr[start++];
            } else {
                sum += arr[++end];
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void printAnswer() {
        if (answer == Integer.MAX_VALUE) {
            System.out.println(0);
            return;
        }

        System.out.println(answer);
    }
}
