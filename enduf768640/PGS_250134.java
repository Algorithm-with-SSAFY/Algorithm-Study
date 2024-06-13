class PGS_250134 {

    private int[][] maze;

    private int height, width;

    private boolean[][] visitedRed, visitedBlue;
    private boolean redEnd, blueEnd;

    private int[] dy = {-1, 1, 0, 0};
    private int[] dx = {0, 0, -1, 1};

    private int answer;

    public int solution(int[][] maze) {
        init(maze);

        moveCart(getRedStart(), getBlueStart(), 0);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private Location getRedStart() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (maze[y][x] == 1) {
                    visitedRed[y][x] = true;
                    return new Location(y, x);
                }
            }
        }

        throw new RuntimeException("시작점을 찾을 수 없습니다.");
    }

    private Location getBlueStart() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (maze[y][x] == 2) {
                    visitedBlue[y][x] = true;
                    return new Location(y, x);
                }
            }
        }

        throw new RuntimeException("시작점을 찾을 수 없습니다.");
    }

    private void moveCart(Location red, Location blue, int depth) {
        if (redEnd && blueEnd) {
            answer = Math.min(answer, depth);
            return;
        }

        for (int redDirection = 0; redDirection < 4; redDirection++) {
            for (int blueDirection = 0; blueDirection < 4; blueDirection++) {
                Location nextRed = redEnd ? red : getNext(red, redDirection);
                Location nextBlue = blueEnd ? blue : getNext(blue, blueDirection);

                if (!isPossible(red, nextRed, blue, nextBlue)) {
                    continue;
                }

                visitedRed[nextRed.getY()][nextRed.getX()] = true;
                visitedBlue[nextBlue.getY()][nextBlue.getX()] = true;

                redEnd = (maze[nextRed.getY()][nextRed.getX()] == 3);
                blueEnd = (maze[nextBlue.getY()][nextBlue.getX()] == 4);

                moveCart(nextRed, nextBlue, depth + 1);

                redEnd = false;
                blueEnd = false;

                visitedRed[nextRed.getY()][nextRed.getX()] = false;
                visitedBlue[nextBlue.getY()][nextBlue.getX()] = false;
            }
        }
    }

    private boolean isPossible(Location red, Location nextRed, Location blue, Location nextBlue) {
        if (nextRed.getY() < 0 || nextRed.getY() >= height || nextRed.getX() < 0 || nextRed.getX() >= width) {
            return false;
        }

        if (nextBlue.getY() < 0 || nextBlue.getY() >= height || nextBlue.getX() < 0 || nextBlue.getX() >= width) {
            return false;
        }

        if (!redEnd && visitedRed[nextRed.getY()][nextRed.getX()]) {
            return false;
        }

        if (!blueEnd && visitedBlue[nextBlue.getY()][nextBlue.getX()]) {
            return false;
        }

        if (maze[nextRed.getY()][nextRed.getX()] == 5) {
            return false;
        }

        if (maze[nextBlue.getY()][nextBlue.getX()] == 5) {
            return false;
        }

        if (nextRed.getY() == nextBlue.getY() && nextRed.getX() == nextBlue.getX()) {
            return false;
        }

        if (nextRed.getY() == blue.getY() && nextRed.getX() == blue.getX()
                && nextBlue.getY() == red.getY() && nextBlue.getX() == red.getX()) {
            return false;
        }

        return true;
    }

    private Location getNext(Location location, int direction) {
        return new Location(location.getY() + dy[direction], location.getX() + dx[direction]);
    }

    private void init(int[][] maze) {
        this.maze = maze;

        height = maze.length;
        width = maze[0].length;

        visitedRed = new boolean[height][width];
        visitedBlue = new boolean[height][width];

        answer = Integer.MAX_VALUE;
    }

    private static class Location {

        private int y;
        private int x;

        public Location(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }
}