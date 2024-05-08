import java.io.*;
import java.util.*;

class Road implements Comparable<Road> {
    int end, distance;

    public Road(int end, int distance) {
        this.end = end;
        this.distance = distance;
    }

    @Override
    public int compareTo(Road o) {
        return this.distance - o.distance;
    }
}

public class BOJ_1446 {
    static int N, D, s, e, d;
    static List<Road>[] roads;
    static int[] distance;

    static void helper(int start) {
        if (start >= D) {
            return;
        }
        if (distance[start + 1] > distance[start] + 1) {
            distance[start + 1] = distance[start] + 1;
        }

        for (Road road : roads[start]) {
            if (road.end > D) {
                continue;
            }

            if (distance[road.end] > distance[start] + road.distance) {
                distance[road.end] = distance[start] + road.distance;
            }
        }

        helper(start + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        roads = new ArrayList[D + 1];
        distance = new int[D + 1];
        for (int i = 0; i < D + 1; i++) {
            distance[i] = i;
            roads[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            if (s > e || s > D) {
                continue;
            }
            roads[s].add(new Road(e, d));
        }
        helper(0);
        bw.write(String.valueOf(distance[D]));

        br.close();
        bw.close();
    }
}