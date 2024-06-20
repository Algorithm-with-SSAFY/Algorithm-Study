import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_21939 {
	static class Node implements Comparable<Node>{
		int num;
		int level;
		public Node(int num, int level) {
			this.num = num;
			this.level = level;
		}
		@Override
		public String toString() {
			return "Node [num=" + num + ", level=" + level + "]";
		}
		@Override
		public int compareTo(Node o) {
			if(o.level==this.level) return this.num-o.num;
			return this.level-o.level;
		}
	}
	static TreeSet<Node> tree;
	static HashMap<Integer, Integer> hash;
	public static void main(String[] args) throws IOException {
		tree = new TreeSet<>();
		hash = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			insert(p,l);
		}
		int M = Integer.parseInt(br.readLine());
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			if(order.equals("add")) {
				int p =Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());
				insert(p,l);
			}
			else if(order.equals("solved")) {
				int p = Integer.parseInt(st.nextToken());
				tree.remove(new Node(p,hash.get(p)));
				hash.remove(p);
			}
			else if(order.equals("recommend")) {
				int t = Integer.parseInt(st.nextToken());
				if(t==1) {
					System.out.println(tree.last().num);
				}else {
					System.out.println(tree.first().num);
				}
			}
		}
	}
	public static void insert(int p,int l) {
		tree.add(new Node(p,l));
		hash.put(p,l);
	}
}
