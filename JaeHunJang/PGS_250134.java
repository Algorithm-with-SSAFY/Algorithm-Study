// 수레 움직이기 / 120분

class PGS_250134 {
    class Pos{
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    private static final int MAX = 999999;

    int[][] map;
    int deltas[][] = {{1,0},{-1,0},{0,1},{0,-1}};
    boolean redEnd, blueEnd;
    boolean[][][] visited;
    
    public int solution(int[][] maze) {
        Pos rs = null;
        Pos bs = null;

        map = new int[maze.length][maze[0].length];
        visited = new boolean[maze.length][maze[0].length][2];
        
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[i].length; j++){
                map[i][j] = maze[i][j];
                // 각 수레의 시작위치 초기화
                if(maze[i][j] == 1) rs = new Pos(i,j);
                else if(maze[i][j] == 2) bs = new Pos(i,j);
            }
        }
        // 시작 위치 방문 처리 (0은 빨간 수레, 1은 파란 수레)
        visited[rs.r][rs.c][0] = true;
        visited[bs.r][bs.c][1] = true;
        int answer = dfs(rs,bs,0);
        return (answer == MAX)? 0 : answer;
    }
	
    // 해당 방향으로 움직임 반환
    private Pos getNext(int r, int c, int dir){
        int nr = r + deltas[dir][0];
        int nc = c + deltas[dir][1];
        return new Pos(nr,nc);
    }
    
    // 해당 방향으로 움직이는 것이 가능한지 판단
    // (현재 빨간 수레 , 다음 빨간 수레, 현재 파란 수레, 다음 파란 수레)
    private boolean isPossible(Pos cntRed, Pos red, Pos cntBlue, Pos blue){
        if(red.r < 0 || red.c < 0 || red.r >= map.length || red.c >= map[0].length
          || blue.r < 0 || blue.c < 0 || blue.r >= map.length || blue.c >= map[0].length
          || map[red.r][red.c] == 5 || map[blue.r][blue.c] == 5) return false;
        
        if((cntRed.r == blue.r && cntRed.c == blue.c)
          && (cntBlue.r == red.r && cntBlue.c == red.c)) return false;
        
        if((!redEnd && visited[red.r][red.c][0])
           || (!blueEnd && visited[blue.r][blue.c][1])) return false;

        if(red.r == blue.r && red.c == blue.c) return false;
        return true;
    }
    
    private int dfs(Pos red, Pos blue, int result){
        if(redEnd && blueEnd) return result;
        int answer = MAX;
        
        for(int i = 0; i < deltas.length; i++){
            for(int j = 0; j < deltas.length; j++){
            	// 도착지점에 도착한 경우엔 움직이지 않음
                Pos nRed = (!redEnd) ? getNext(red.r,red.c,i) : red;
                Pos nBlue = (!blueEnd) ? getNext(blue.r,blue.c,j) : blue;
                
                if(!isPossible(red,nRed,blue,nBlue)) continue;
                visited[nRed.r][nRed.c][0] = true;
                visited[nBlue.r][nBlue.c][1] = true;
                if(map[nRed.r][nRed.c] == 3) redEnd = true;
                if(map[nBlue.r][nBlue.c] == 4) blueEnd = true;
                
                answer = Math.min(answer, dfs(nRed,nBlue,result+1));

                redEnd = false;
                blueEnd = false;
                visited[nRed.r][nRed.c][0] = false;
                visited[nBlue.r][nBlue.c][1] = false;
            }
        }
        return answer;
    }
}