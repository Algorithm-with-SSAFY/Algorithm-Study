import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static class PL implements Comparable<PL>{
		int P;
		int L;
		public PL(int p, int l) {
			super();
			P = p;
			L = l;
		}
		@Override
		public int compareTo(Main.PL o) {
			if(this.L==o.L) return this.P-o.P;
			return this.L-o.L;
		}
		
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<Integer,Integer> map = new HashMap<>();
		TreeSet<PL> ts = new TreeSet<>(); //treeset 이용 중요 !
		for(int i = 0 ; i<N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			map.put(P, L);
			ts.add(new PL(P,L));
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			String[] order = br.readLine().split(" ");
			if(order[0].equals("add")) {
				ts.add(new PL(Integer.parseInt(order[1]), Integer.parseInt(order[2])));
				map.put(Integer.parseInt(order[1]), Integer.parseInt(order[2]));
			}
			else if(order[0].equals("solved")) {
				int nowidx = Integer.parseInt(order[1]);
				ts.remove(new PL(nowidx,map.get(nowidx))); //treeset 해당값 지우기 !
				map.remove(nowidx);
			}
			else {
				if(Integer.parseInt(order[1])==1) System.out.println(ts.last().P); // last() 함수 이용
				else System.out.println(ts.first().P); // first() 함수 이용
			}
		}
		
	}

}
