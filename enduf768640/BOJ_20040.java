import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20040 {

    private static int N, M;

    private static Node[] nodes;
    private static Input[] inputs;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        countStage();

        printAnswer();
    }

    private static void countStage() {
        for (Input input : inputs) {
            int idx1 = input.getIdx1();
            int idx2 = input.getIdx2();

            answer++;

            if (!union(nodes[idx1], nodes[idx2])) {
                return;
            }
        }

        answer = 0;
    }

    private static Node findSet(Node node) {
        if (node.getParent() == node) {
            return node;
        }

        Node root = findSet(node.getParent());
        node.setParent(root);
        return root;
    }

    private static boolean union(Node node1, Node node2) {
        if (findSet(node1) == findSet(node2)) {
            return false;
        }

        findSet(node1).setParent(findSet(node2));
        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node();
        }

        inputs = new Input[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int idx1 = Integer.parseInt(st.nextToken());
            int idx2 = Integer.parseInt(st.nextToken());

            inputs[i] = new Input(idx1, idx2);
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private static class Node {

        private Node parent;

        public Node() {
            this.parent = this;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }

    private static class Input {

        private int idx1;
        private int idx2;

        public Input(int idx1, int idx2) {
            this.idx1 = idx1;
            this.idx2 = idx2;
        }

        public int getIdx1() {
            return idx1;
        }

        public int getIdx2() {
            return idx2;
        }
    }
}
