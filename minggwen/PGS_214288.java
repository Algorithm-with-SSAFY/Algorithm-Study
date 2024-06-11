// 프로그래머스 상담원 인원

import java.util.*;
class Solution {
    static int K, N, REQS[][];
    static int MIN = Integer.MAX_VALUE;
    public int solution(int k, int n, int[][] reqs) {
        
        K=k;
        N=n;
        REQS = reqs;
        
        int arr[] = new int[k];
        recur(arr,0,0);
        
        return MIN;
    }
    public void recur(int arr[],int idx,int num){
        if(idx==K){
            calc(arr);
        }
        else if(idx==K-1){
            arr[idx] = N-num;
            recur(arr,idx+1,K);
        }
        else{
            for(int i=1;i<=N-K+1;i++){
                if(num+i>=N)return;
                arr[idx]=i;
                recur(arr,idx+1,num+i);
            }
        }
    }
    public void calc(int[] arr){
        PriorityQueue<Integer> [] que = new PriorityQueue [K];
        int time = 0;
        for(int i=0;i<K;i++){
            que[i] = new PriorityQueue<>();
            for(int j=0;j<arr[i];j++){
                que[i].add(0);
            }
        }

        for(int[] req:REQS){
            int start = req[0];
            int end = req[1];
            int type = req[2]-1;
            if(que[type].peek()<start){
                que[type].poll();
                que[type].add(start+end);
            }else{
                time+=que[type].peek()-start;
                que[type].add(que[type].poll()+end);
            }
        }
        MIN=MIN>time?time:MIN;
    }
    
}
