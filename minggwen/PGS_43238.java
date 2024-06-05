// 프로그래머스 입국 심사
import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long left = 0;
        Arrays.sort(times);
        long right = times[times.length-1]*(long)n;
        while(left<=right){
            long mid = (left+right)/2;
            long sum = 0;
            for(int time:times){
                sum+=(mid/time);
                if(sum>n) break;
            }
            if(sum<n){
                left = mid+1;
            }else{
                answer = mid;
                right = mid-1;
            }
        }
        return answer;
    }
}
