const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "test.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

class Node {
    constructor(x, y, cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

const bfs = ()=> {
    let q = [];
    q.push(new Node(sx,sy,0));
    visited[sx][sy] = true;

    while (q.length) {
        let node = q.shift();
        let x = node.x;
        let y = node.y;
        let cnt = node.cnt;

        if (x==gx && y==gy) {
            answer = cnt;
            return;
        }

        for (let d = 0; d < 4; d++) {
            let nx = x + dx[d];
            let ny = y + dy[d];
            
            if (isValid(nx, ny)) {
                q.push(new Node(nx,ny, cnt+1));
                visited[nx][ny] = true;
            }
        }
    }
}

const isValid = (x, y)=> {
    if (x < 0 || y < 0 || x + h > n || y + w > m) {
        return false;
    }

    if (visited[x][y]) {
        return false;
    }

    for (let i = x; i < x + h; i++) {
        for (let j = y; j < y + w; j++) {
            if (map[i][j] === 1) {
                return false;
            }   
        }
    }

    return true;
}

let answer = -1;
const dx = [0,1,0,-1];
const dy = [-1,0,1,0];
let [n, m] = input.shift().trim().split(" ").map(item=>+item);
let map = new Array(n).fill().map(i=>[]);
let visited = new Array(n).fill().map(i=>new Array(m).fill(false));
for (let i = 0; i < n; i++) {
    map[i] = input.shift().trim().split(" ").map(item=>+item);
}
let [h,w,sx,sy,gx,gy] = input.shift().trim().split(" ").map(item=>+item);
sx--;
sy--;
gx--;
gy--;

bfs();
console.log(answer)
