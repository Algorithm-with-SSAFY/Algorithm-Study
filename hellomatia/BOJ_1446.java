package hellomatia;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1446 {

    class Node {
        int to;
        int dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, D;
    private int[] minDist;
    private List<Node>[] nodes;

    public static void main(String[] args) throws IOException {
        new BOJ_1446().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        nodes = new List[10_000 + 1];
        minDist = new int[10_000 + 1];
        for (int i = 0; i <= 10_000; i++) {
            nodes[i] = new LinkedList<>();
            minDist[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            nodes[start].add(new Node(end, dist));
        }
    }

    private int calcAns() {
        calcMinDist(0);
        return minDist[D];
    }

    private void calcMinDist(int start) {
        if (start > D) {
            return;
        }

        minDist[start + 1] = Math.min(minDist[start + 1], minDist[start] + 1);

        for (int i = 0; i < nodes[start].size(); i++) {
            minDist[nodes[start].get(i).to] = Math.min(
                    minDist[nodes[start].get(i).to],
                    minDist[start] + nodes[start].get(i).dist
            );
        }

        calcMinDist(start + 1);
    }

    private void printAns(int ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}
