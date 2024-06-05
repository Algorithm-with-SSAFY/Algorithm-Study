package PGS;

public class PGS_43238 {
    class Solution {
        private long answer;

        public long solution(int n, int[] times) {
            long maxTime = (long)1000000000 * (long)1000000000;
            long minTime = 1;
            answer = maxTime;
            search(times,n,minTime,maxTime);
            return answer;
        }

        private void search(int[] times, int goal, long start, long end){
            while(start <= end){
                long mid = (start + end) / 2;

                long timeCnt = 0;
                for(int time : times){
                    timeCnt += (mid / time);
                }

                if(goal <= timeCnt){
                    answer = Math.min(answer,mid);
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
        }
    }
}
