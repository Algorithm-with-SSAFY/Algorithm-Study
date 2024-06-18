import java.io.*;
import java.util.*;

public class BOJ_21939 {
    static class Problem implements Comparable<Problem> {
        int num, difficulty;

        public Problem(int num, int difficulty) {
            this.num = num;
            this.difficulty = difficulty;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.difficulty == o.difficulty) {
                return this.num - o.num;
            }
            return this.difficulty - o.difficulty;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Problem> maxPq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Problem> minPq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            Problem problem = new Problem(P, L);
            maxPq.add(problem);
            minPq.add(problem);
            map.put(P, L);
        }

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();
            int P, L;
            switch (command) {
                case "recommend":
                    P = Integer.parseInt(st.nextToken());
                    if (P == 1) {
                        while (map.get(maxPq.peek().num) == null || map.get(maxPq.peek().num) != maxPq.peek().difficulty) {
                            maxPq.poll();
                        }
                        sb.append(maxPq.peek().num).append("\n");
                    } else {
                        while (map.get(minPq.peek().num) == null || map.get(minPq.peek().num) != minPq.peek().difficulty) {
                            minPq.poll();
                        }
                        sb.append(minPq.peek().num).append("\n");
                    }
                    break;
                case "add":
                    P = Integer.parseInt(st.nextToken());
                    L = Integer.parseInt(st.nextToken());
                    Problem problem = new Problem(P, L);
                    maxPq.add(problem);
                    minPq.add(problem);
                    map.put(P, L);
                    break;
                case "solved":
                    P = Integer.parseInt(st.nextToken());
                    map.remove(P);
                    break;
            }
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}