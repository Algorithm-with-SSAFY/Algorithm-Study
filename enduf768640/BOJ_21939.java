import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_21939 {

    private static int N, M;

    private static Map<Integer, Problem> problemMap;
    private static TreeSet<Problem> problems;
    private static String[] commands;

    public static void main(String[] args) throws IOException {
        init();

        executeCommands();
    }

    private static void executeCommands() {
        for (String command : commands) {
            String[] split = command.split("\\s");

            if (split[0].equals("recommend")) {
                Problem problem = split[1].equals("1") ? problems.last() : problems.first();
                System.out.println(problem.getNumber());
            } else if (split[0].equals("add")) {
                int number = Integer.parseInt(split[1]);
                int difficulty = Integer.parseInt(split[2]);

                Problem problem = new Problem(number, difficulty);
                problems.add(problem);
                problemMap.put(number, problem);
            } else {
                int number = Integer.parseInt(split[1]);

                Problem problem = problemMap.get(number);
                problems.remove(problem);
                problemMap.remove(number);
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        problemMap = new HashMap<>();

        N = Integer.parseInt(br.readLine());
        problems = new TreeSet<>((p1, p2) ->
                p1.getDifficulty() == p2.getDifficulty() ? p1.getNumber() - p2.getNumber()
                        : p1.getDifficulty() - p2.getDifficulty()
        );
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int number = Integer.parseInt(st.nextToken());
            int difficulty = Integer.parseInt(st.nextToken());
            Problem problem = new Problem(number, difficulty);

            problemMap.put(number, problem);
            problems.add(problem);
        }

        M = Integer.parseInt(br.readLine());
        commands = new String[M];
        for (int i = 0; i < M; i++) {
            commands[i] = br.readLine();
        }
    }

    private static class Problem {

        private int number;
        private int difficulty;

        public Problem(int number, int difficulty) {
            this.number = number;
            this.difficulty = difficulty;
        }

        public int getNumber() {
            return number;
        }

        public int getDifficulty() {
            return difficulty;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Problem problem = (Problem) o;
            return number == problem.number && difficulty == problem.difficulty;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number, difficulty);
        }
    }
}
