import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 문제 추천 시스템 Version 1 / 60분
public class BOJ_21939 {
    static class Quest {
        int P, L;

        // 문제번호, 난이도
        public Quest(int p, int l) {
            P = p;
            L = l;
        }

        public boolean equals(Object q) {
            return ((Quest) q).P == this.P;
        }

        public String toString() {
            return "Quest{" +
                    "P=" + P +
                    ", L=" + L +
                    '}';
        }
    }

    static PriorityQueue<Quest> list;
    static PriorityQueue<Quest> list2;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        list = new PriorityQueue<>((o1, o2) -> {
            if (o1.L == o2.L) { // 난이도가 같으면 번호순으로
                return Integer.compare(o1.P, o2.P);
            }
            return Integer.compare(o1.L, o2.L);
        });
        list2 = new PriorityQueue<>((o1, o2) -> {
            if (o1.L == o2.L) { // 난이도가 같으면 번호순으로
                return Integer.compare(o2.P, o1.P);
            }
            return Integer.compare(o2.L, o1.L);
        });
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Quest q = new Quest(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            list.offer(q);
            list2.offer(q);
        }

        int M = Integer.parseInt(br.readLine());
        int cmd = 0;
        Quest q;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "add" :
                    q = new Quest(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                    list.offer(q);
                    list2.offer(q);
                    break;
                case "recommend" :
                    cmd = Integer.parseInt(st.nextToken());
                    if (cmd == -1) {
                        sb.append(list.peek().P);
                    } else if (cmd == 1) {
                        sb.append(list2.peek().P);
                    }
                    sb.append("\n");
                    break;
                case "solved" :
                    q = new Quest(Integer.parseInt(st.nextToken()), 0);
                    list.remove(q);
                    list2.remove(q);
                    break;
            }
            q = null;
        }
        System.out.println(sb.toString());
    }
}