import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20040 {
	static int parent[];
	static boolean visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parent =new int [N];
		List<List<Integer>> visited = new ArrayList<>();
		boolean flag = false;
		int cnt = 0;
		for(int n=0;n<N;n++) {
			parent[n]=n;
			visited.add(new ArrayList<>());
		}
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			if(flag)continue;
			cnt++;
			if(visited.get(a).contains(b))continue;
			if(union(a,b)) {
				visited.get(a).add(b);
				visited.get(b).add(a);
			}else {
				flag=true;
			}
			
		}
		System.out.println(flag?cnt:0);
	}
	private static int find(int num) {
		if(parent[num]!=num) return find(parent[num]);
		return num;
	}
	private static boolean union(int a,int b) {
		int ap = find(a);
		int bp = find(b);
		if(ap==bp)return false;
		if(ap<bp) {
			parent[bp]=ap;
		}else {
			parent[ap]=bp;
		}
		return true;
	}
}
