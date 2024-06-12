//프로그래머스 에어컨
import java.util.*;
class Solution {
    static int T1,T2;
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = Integer.MAX_VALUE;
        t1+=10;
        t2+=10;
        temperature+=10;
        T1=t1;
        T2=t2;
        int dp[][] = new int [onboard.length+1][51];
        int up,down;
        if(temperature>t2){
            up = -1;
            down = 1;
        }else{
            up = 1;
            down = -1;
        }
        for(int i=0;i<=onboard.length;i++){
            for(int j=0;j<=50;j++){
                dp[i][j]=101*1000;
            }
        }
        dp[0][temperature] = 0;
        for(int i=1;i<onboard.length;i++){
            for(int j=0;j<=50;j++){
                if(onboard[i]==1&&!isComf(j))continue;
                if(isIn(j+up)) dp[i][j]=Math.min(dp[i-1][j+up],dp[i][j]);
                if(isIn(j+down)) dp[i][j]=Math.min(dp[i-1][j+down]+a,dp[i][j]);
                if(j==temperature)dp[i][j] = Math.min(dp[i-1][j],dp[i][j]);
                if(isComf(j)) dp[i][j]=Math.min(dp[i-1][j]+b,dp[i][j]);
            }
        }
        for(int j=0;j<=50;j++) answer = Math.min(answer,dp[onboard.length-1][j]);
        
        return answer;
    }
    static private boolean isIn(int num){
        return 0<=num&&num<=50?true:false;
    }
    static private boolean isComf(int num){
        return T1<=num&&num<=T2?true:false;
    }
}                         
