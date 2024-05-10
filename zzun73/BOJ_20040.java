import java.io.*;
import java.util.StringTokenizer;

public class BOJ_20040 {
    static int N, M, answer;
    static int[] root;

    static void init() {
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }
    }

    static int find(int x) {
        if (x == root[x]) {
            return x;
        }
        return root[x] = find(root[x]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (root[pa] < root[pb]) {
            root[pb] = pa;
        } else {
            root[pa] = pb;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        root = new int[N];
        init();

        answer = 0;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (find(a) == find(b)) {
                answer = i;
                break;
            } else {
                union(a, b);
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}