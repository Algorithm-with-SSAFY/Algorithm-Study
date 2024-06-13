// 프로그래머스 수레 움직이기

import java.util.*;
class Solution {
    static int map[][],mr,mc; 
    static int[][] delta = {{-1,0},{1,0},{0,1},{0,-1}};
    static boolean redVisited[][];
    static boolean blueVisited[][];
    static int MIN = Integer.MAX_VALUE;
    public int solution(int[][] maze) {
        map = maze;
        mr = map.length;
        mc = map[0].length;
        redVisited = new boolean [mr][mc];
        blueVisited = new boolean [mr][mc];
        int redR=0; 
        int redC=0; 
        int blueR=0; 
        int blueC=0;
        for(int r=0;r<mr;r++){
            for(int c=0;c<mc;c++){
                if(map[r][c]==1){
                    redR = r;
                    redC=c;
                }
                else if(map[r][c]==2){
                    blueR=r;
                    blueC=c;
                }
            }
            
        }
        redVisited[redR][redC] = true;
        blueVisited[blueR][blueC] = true;
        dfs(redR,redC,blueR,blueC,0);
        return MIN==Integer.MAX_VALUE?0:MIN;
    }
    public void dfs(int redR, int redC, int blueR, int blueC,int move){
        if(map[redR][redC]==3&&map[blueR][blueC]==4){
            MIN = Math.min(move,MIN);
            return;
        }
        
        List<int[]> red = new ArrayList<>();
        List<int[]> blue = new ArrayList<>();
        if(map[redR][redC]==3){
            red.add(new int[] {redR,redC});
        }else{
            for(int d=0;d<4;d++){
                int rd = redR+delta[d][0];
                int rc = redC+delta[d][1];
                if(isIn(rd,rc)&&!redVisited[rd][rc]&&map[rd][rc]!=5){
                    red.add(new int[] {rd,rc});
                }
            }
        }
        if(map[blueR][blueC]==4){
            blue.add(new int[] {blueR,blueC});
        }else{
            for(int d=0;d<4;d++){
                int rd = blueR+delta[d][0];
                int rc = blueC+delta[d][1];
                if(isIn(rd,rc)&&!blueVisited[rd][rc]&&map[rd][rc]!=5){
                    blue.add(new int[] {rd,rc});
                }
            }
        }
        
        for(int[] redRoute:red){
            for(int[] blueRoute:blue){
                if(redRoute[0]==blueRoute[0]&&redRoute[1]==blueRoute[1]){
                    continue;
                }
                else if(redRoute[0]==blueR&&redRoute[1]==blueC
                      &&blueRoute[0]==redR&&blueRoute[1]==redC){
                        continue;
                }
                else{
                    redVisited[redRoute[0]][redRoute[1]]=true;
                    blueVisited[blueRoute[0]][blueRoute[1]]=true;
                    dfs(redRoute[0],redRoute[1],blueRoute[0],blueRoute[1],move+1);
                    redVisited[redRoute[0]][redRoute[1]]=false;
                    blueVisited[blueRoute[0]][blueRoute[1]]=false;
                }
            }
        }
    }
    static boolean isIn(int row, int col){
        return 0<=row&&row<mr&&0<=col&&col<mc;
    }
}
