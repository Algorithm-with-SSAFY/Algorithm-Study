import java.util.*;

// 석유 시추 / 60분
class PGS_250136 {
    class Pos {
        int r, c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public String toString() {
            return "r: " + r + ", c: " + c;
        }
    }
    int deltas[][] = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    int visited[][];
    Map<Integer, Integer> infos;
    public int solution(int[][] land) {
        int answer = 0;
        infos = new HashMap();
        visited = new int[land.length][land[0].length];
        int marker = 1;
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (visited[i][j] > 0 || land[i][j] == 0) continue;
                int count = bfs(land, new Pos(i, j), marker);
                if (count > 0) {
                    infos.put(marker, count);
                    marker++;
                } 
            }
        }
        
        for (int j = 0; j < land[0].length; j++) {
            Set<Integer> set = new HashSet();
            for (int i = 0; i < land.length; i++) {
                if (land[i][j] == 0) continue;
                set.add(visited[i][j]);
            }
            int sum = 0;
            for (int key : set) {
                sum += infos.get(key);
            }
            answer = Math.max(answer, sum);
        }
        return answer;
    }
    
    int bfs(int[][] land, Pos start, int marker) {
        Queue<Pos> q = new ArrayDeque();
        q.offer(start);
        // h.add(start);
        int cnt = 1;
        visited[start.r][start.c] = marker;
        
        while(!q.isEmpty()) {
            Pos now = q.poll();
            for (int d = 0; d < deltas.length; d++) {
                int nr = now.r + deltas[d][0];
                int nc = now.c + deltas[d][1];
                
                if (nr < 0 || nr >= land.length || nc < 0 || nc >= land[0].length || visited[nr][nc] > 0 || land[nr][nc] == 0) continue;
                Pos next = new Pos(nr, nc);
                visited[next.r][next.c] = marker;
                // h.add(next);
                cnt++;
                q.offer(next);
            }
        }
        return cnt;
    }
}