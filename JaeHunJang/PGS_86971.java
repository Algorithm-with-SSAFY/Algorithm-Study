import java.util.*;

// 전력망을 둘로 나누기 / 60분
class PGS_86971 {
    List<Integer>[] list;
    public int solution(int n, int[][] wires) {
        int answer = n;
        
        list = new List[n+1];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList();
        }
        
        for (int i = 0; i < wires.length; i++) {
            list[wires[i][0]].add(wires[i][1]);
            list[wires[i][1]].add(wires[i][0]);
        }
        
        for (int i = 0; i < wires.length; i++) {
            int diff = Math.abs(bfs(wires[i][0], wires[i][1], n) - bfs(wires[i][1], wires[i][0], n));
            answer = Math.min(answer, diff);
        }
        
        return answer;
    }
    
    int bfs(int start, int none, int n) {
        Queue<Integer> q = new ArrayDeque();
        q.offer(start);
        boolean visited[] = new boolean[n+1];
        visited[start] = true;
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int now = q.poll();
            cnt++;
            
            for (int next : list[now]) {
                if (visited[next] || next == none) continue;
                visited[next] = true;
                q.offer(next);
            }
        }
        
        return cnt;
    }
}