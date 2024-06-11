package PGS;
import java.util.*;

public class PGS_250136 {
    class Solution {
        private static class Node {
            int x;
            int y;

            public Node(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        private static int[] dx = {-1, 1, 0, 0};
        private static int[] dy = {0, 0, -1, 1};
        private static int xSize;
        private static int ySize;
        static int[][] oil;
        static int size = 1;
        static Map<Integer,Integer> sizeInfo = new HashMap<>();
        static boolean[][] visited;

        public int solution(int[][] land) {
            int answer = 0;
            xSize = land.length;
            ySize = land[0].length;
            visited = new boolean[xSize][ySize];
            oil = new int[xSize][ySize];


            for (int x = 0; x < xSize; x++) {
                for (int y = 0; y < ySize; y++) {
                    if (land[x][y] == 1 && !visited[x][y]) {
                        bfs(land, x, y);
                    }
                }
            }


            for (int y = 0; y < ySize; y++) {
                int columnOil = 0;
                Set<Integer> set = new HashSet<>();

                for (int x = 0; x < xSize; x++) {
                    if (oil[x][y] > 0) {
                        set.add(oil[x][y]);
                    }
                }

                for(Integer val : set){
                    columnOil += sizeInfo.get(val);
                }
                answer = Math.max(answer, columnOil);
            }

            return answer;
        }

        private static void bfs(int[][] land, int x, int y) {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(new Node(x, y));
            visited[x][y] = true;
            oil[x][y] = size;
            int oilSize = 0;

            while (!queue.isEmpty()) {
                Node now = queue.poll();
                oilSize++;

                for (int d = 0; d < 4; d++) {
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];

                    if (!isValid(nx, ny) || visited[nx][ny] || land[nx][ny] == 0) continue;

                    queue.offer(new Node(nx, ny));
                    oil[nx][ny] = size;
                    visited[nx][ny] = true;
                }
            }
            sizeInfo.put(size++,oilSize);
        }//bfs end

        private static boolean isValid(int x, int y) {
            return x >= 0 && x < xSize && y >= 0 && y < ySize;
        }
    }

}
