import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1446 {

    private static int N, D;
    private static Map<Integer, List<Shortcut>> shortcuts;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        dfs(0, 0);

        printAnswer();
    }

    private static void dfs(int distance, int weight) {
        if (distance > D) {
            return;
        }

        if (distance == D) {
            answer = Math.min(answer, weight);
            return;
        }

        if (shortcuts.containsKey(distance)) {
            for (Shortcut shortcut : shortcuts.get(distance)) {
                dfs(shortcut.getEnd(), weight + shortcut.getWeight());
            }
        }

        dfs(distance + 1, weight + 1);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        shortcuts = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if (!shortcuts.containsKey(start)) {
                shortcuts.put(start, new ArrayList<>());
            }

            shortcuts.get(start).add(new Shortcut(end, weight));
        }

        answer = D;
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private static class Shortcut {

        private int end;
        private int weight;

        public Shortcut(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        public int getEnd() {
            return end;
        }

        public int getWeight() {
            return weight;
        }
    }
}
