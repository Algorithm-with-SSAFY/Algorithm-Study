import java.util.*;

// 상담원 인원 / 90분
class PGS_214288 {
    class Job {
        int req, time;
        public Job(int req, int time) {
            this.req = req;
            this.time = time;
        }
        public String toString() {
            return "req:" + req + ", time:" + time;
        }
    }
    List<Job> list[];
    int mentos[];
    int answer = Integer.MAX_VALUE;
    public int solution(int k, int n, int[][] reqs) {
        
        list = new List[k];
        mentos = new int[k];
        for (int i = 0; i < k; i++) {
            list[i] = new ArrayList();
        }
        for (int[] req : reqs) {
            list[req[2]-1].add(new Job(req[0], req[1]));
        }
        dfs(n-k, k, 0, 0);
        return answer;
    }
    
    void dfs(int n, int k, int cnt, int start) {
        if (cnt == n) {
            answer = Math.min(answer, calc());
            return;
        }
        
        for (int i = start; i < k; i++) {
            mentos[i]++;
            dfs(n, k, cnt + 1, i);
            mentos[i]--;
        }
    }
    
    int calc() {
        int totalTime = 0;
        for (int i = 0; i < list.length; i++) {
            totalTime += getReadyTime(list[i], mentos[i]+1);
        }
        return totalTime;
    }
    
    int getReadyTime(List<Job> jobs, int agents) {
        if (jobs.isEmpty()) return 0;

        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i < agents; i++) {
            q.offer(0);
        }

        int totalWaitTime = 0; 
        for (Job job : jobs) {
            int prevTime = q.poll(); 
            int startTime = Math.max(prevTime, job.req); 
            totalWaitTime += startTime - job.req; 
            q.offer(startTime + job.time); 
        }

        return totalWaitTime;
    }
}