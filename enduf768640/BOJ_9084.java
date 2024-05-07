import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9084 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int T, N, M;
    private static int[] coins;

    private static int[] dp;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            init();

            makeDPTable();

            printAnswer();
        }
    }

    private static void makeDPTable() {
        dp = new int[M + 1];

        for (int i = 0; i < coins.length; i++) {
            if (M < coins[i]) {
                break;
            }

            dp[coins[i]]++;

            for (int money = coins[i]; money <= M; money++) {
                dp[money] += dp[money - coins[i]];
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
    }

    private static void printAnswer() {
        System.out.println(dp[M]);
    }
}
