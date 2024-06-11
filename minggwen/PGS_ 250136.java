//프로그래머스 [PCCP 기출문제] 2번 / 석유 시추

import java.util.*;
class Solution {
    static int LAND[][];
    static boolean visited[][];
    static int R;
    static int C;
    static int arr[];
    static int delta[][] = {{-1,0},{1,0},{0,1},{0,-1}};
    public int solution(int[][] land) {
        int answer = 0;
        LAND = land;
        R = LAND.length;
        C = LAND[0].length;
        arr = new int[C];
        visited= new boolean[R][C];
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                if(LAND[r][c]==1&&!visited[r][c]){
                    bfs(r,c);
                }
            }
        }
        for(int a:arr){
            answer = answer>a?answer:a;
        }
        return answer;
    }
    private void bfs(int row,int col){
        Queue<int[]> que = new ArrayDeque<>();
        boolean visitedC[] = new boolean[C];
        que.add(new int[]{row,col});
        visited[row][col] = true;
        int cnt = 0;
        while(!que.isEmpty()){
            int r = que.peek()[0];
            int c = que.peek()[1];
            que.poll();
            cnt++;
            if(!visitedC[c]){
                visitedC[c]=true;
            }
            for(int i=0;i<delta.length;i++){
                int rd = r+delta[i][0];
                int cd = c+delta[i][1];
                if(isIn(rd,cd)&&LAND[rd][cd]==1&&!visited[rd][cd]){
                    visited[rd][cd] = true;
                    que.add(new int[]{rd,cd});
                }
            }
            
        }
        for(int c=0;c<C;c++){
            if(visitedC[c]){
                arr[c]+=cnt;
            }
        }
    }
    private boolean isIn(int row,int col){
        return 0<=row&&row<R&&0<=col&&col<C?true:false;
    }
}
