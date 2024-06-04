
// 입국심사 / 60분
class PGS_43238 {
    
    private boolean isValid(long t, int n, int[] times){
        long cnt = 0;
        
        for(int time : times){
            cnt += t / time;
        }
        
        return cnt >= n; //계산값이 횟수보다 큰지 검사
        
    }
    
    public long solution(int n, int[] times) {
        long start = 1; //최소 경우의 수(입국심사 1명 * 시간 1분)
        long end = 1000000000000000000L; //최대 경우의 수 (입국심사 1,000,000,000명 * 시간 1,000,000,000분)
        
        while(end > start){
            long t = (start+end) / 2; //중간값
            
            if(isValid(t, n, times)) //계산값이 크면 end 변경 아니면 start 변경
                end = t;
            else
                start = t + 1;
        }
        
        return start;
    }
}