package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_21939 {
    public static class Node implements Comparable<Node> {
        int P;
        int L;
        public Node(int P, int L){
            this.P = P;
            this.L = L;
        }


        @Override
        public int compareTo(Node p) {
            if(this.L ==p.L){
                return this.P - p.P;
            }else{
                return this.L - p.L;
            }
        }
    }
    private static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeSet<Node> tree = new TreeSet<Node>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            tree.add(new Node(p, l));
            map.put(p, l);
        }

        m = Integer.parseInt(br.readLine());

        for(int j = 0; j < m; j++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();

            if(name.equals("recommend")){
                int x = Integer.parseInt(st.nextToken());

                if(x == 1){
                    System.out.println(tree.last().P);
                }else{
                    System.out.println(tree.first().P);
                }
            }else if(name.equals("add")){
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());

                tree.add(new Node(P, L));
                map.put(P, L);
            }else{
                int P = Integer.parseInt(st.nextToken());
                int L = map.get(P);

                tree.remove(new Node(P, L));
                map.remove(P);
            }
        }
    }//main end
}//class end
