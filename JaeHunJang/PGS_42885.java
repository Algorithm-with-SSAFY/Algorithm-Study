import java.util.*;
import java.util.Collections;

// 구명보트 / 60분
class PGS_42885 {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Integer[] sort_people = new Integer[people.length];
        
        for(int i = 0; i < sort_people.length; i++){
            sort_people[i] = people[i];
        }
        
        Arrays.sort(sort_people, Collections.reverseOrder());
   
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int person : sort_people) {
            int calc = limit - person;
            if (pq.isEmpty()) {
                if (calc >= 40)
                    pq.add(limit - person);
                answer++;
            } else {
                if (pq.peek() >= person) {
                    pq.poll();
                } else {
                    if (calc >= 40)
                        pq.add(calc);
                    answer++;
                }
            }
        }
        
        return answer;
        
        //한 명씩 태우는 경우의 수 - 두 명 태우는 경우의 수
        // Arrays.sort(people);
        // int i = 0, j = people.length - 1;
        // for (; i < j; --j) {
        //     if (people[i] + people[j] <= limit)
        //         ++i;
        // }
        // return people.length - i;
    }
}