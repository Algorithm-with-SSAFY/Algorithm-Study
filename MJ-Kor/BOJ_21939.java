import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_21939 {

	public static int N, M;
	
	public static class Problem implements Comparable<Problem>{
		int P, L;
		
		Problem(int p, int l){
			this.P = p;
			this.L = l;
		}
		
		@Override
		public int compareTo(Problem p) {
			
			if(this.L == p.L) {
				return this.P - p.P;
			}
			else {
				return this.L - p.L;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		TreeSet<Problem> asc = new TreeSet<Problem>();
		Map<Integer,Integer> map = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			asc.add(new Problem(P, L));
			map.put(P, L);
		}
		
		M = Integer.parseInt(br.readLine());
		
		for(int j=0; j<M; j++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
						
			if(cmd.equals("recommend")) {
				// recommend x
				int x = Integer.parseInt(st.nextToken());
				
				if(x == 1) {
					//가장 어려운 문제
					System.out.println(asc.last().P);
				}
				else {
					// 가장 쉬운 문제
					System.out.println(asc.first().P);
				}
			} 
			else if(cmd.equals("add")){
				// add P L
				int P = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				
				asc.add(new Problem(P, L));
				map.put(P, L);
			}
			else {
				//solved P
				int P = Integer.parseInt(st.nextToken());
				int L = map.get(P);
				
				asc.remove(new Problem(P, L));
				map.remove(P);
			}
		}
	}
}