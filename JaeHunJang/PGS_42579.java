import java.util.*;

// 베스트앨범 / 60분
class PGS_42579 {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap();
        Map<String, Integer> map2 = new HashMap();
        
        for(int i = 0; i < plays.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
            map2.put(genres[i] + i, plays[i]);
        }
        
        List<String> keySet = new ArrayList<>(map.keySet());
        keySet.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return map.get(o2).compareTo(map.get(o1));
            }
        });
        
        List<String> keySet2 = new ArrayList<>(map2.keySet());
        keySet2.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                if(map2.get(o2).compareTo(map2.get(o1)) == 0)
                    return o1.replaceAll("[a-z|A-Z]", "").compareTo(o2.replaceAll("[a-z|A-Z]", ""));
                else 
                    return map2.get(o2).compareTo(map2.get(o1));
            }
        });
        
        for(String k : keySet) {
            keySet2.stream().filter(genre -> genre.contains(k)).limit(2).forEach(genre -> {
				answer.add(Integer.parseInt(genre.replace(k, "")));
			});
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}