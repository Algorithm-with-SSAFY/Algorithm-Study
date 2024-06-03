import java.util.*;
class Job {
    int req, time;
    public Job(int req, int time) {
        this.req = req;
        this.time = time;
    }
    
    public String toString() {
        return "req : " + req + ", time : " + time;
    }
}

// 디스크 컨트롤러 / 90분
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int currentTime = 0;
        PriorityQueue<Job> reqpq = new PriorityQueue(new Comparator<Job>() {
            public int compare(Job o1, Job o2) {
                return Integer.compare(o1.req, o2.req);
            }
        });
        
        PriorityQueue<Job> timepq = new PriorityQueue(new Comparator<Job>() {
            public int compare(Job o1, Job o2) {
                return Integer.compare(o1.time, o2.time);
            }
        });
        
        for (int[] job : jobs) {
            reqpq.offer(new Job(job[0], job[1]));
        }
        
        // 모든 작업이 처리될 때까지 반복
        while (!reqpq.isEmpty() || !timepq.isEmpty()) {
            // 현재 시간 이전에 도착한 모든 작업을 처리 큐로 이동
            while (!reqpq.isEmpty() && reqpq.peek().req <= currentTime) {
                timepq.offer(reqpq.poll());
            }

            if (!timepq.isEmpty()) {
                Job job = timepq.poll();
                currentTime += job.time;  // 현재 시간 업데이트
                answer += currentTime - job.req;  // 총 대기 시간 계산
            } else {
                currentTime = reqpq.peek().req;
            }
        }
        
        return answer / jobs.length; 
    }
}
