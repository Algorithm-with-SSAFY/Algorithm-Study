const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "test.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

class Node {
  constructor(x, y, d) {
    this.x = x;
    this.y = y;
    this.d = d;
  }
}

const bfs = ()=> {
    const q = [];
    q.push(new Node(0,0,1));
    visited[0][0] = 1;

    while (q.length) {
        let {x, y, d} = q.shift();

        if (x==n-1 && y==m-1) {
            answer = d;
            return;
        }
        
        for (let i = 0; i < 4; i++) {
            let nx = x + dx[i];
            let ny = y + dy[i];

            if (0>nx || 0>ny || n<=nx || m<=ny) {
                continue;
            }

            if (map[nx][ny] === 1 && d + 1 < visited[nx][ny]) {
                q.push(new Node(nx, ny, d+1));
                visited[nx][ny] = d+1;
            }
        }
    }
}

const dx = [-1,0,1,0];
const dy = [0,1,0,-1];
let answer = 0;

let [n, m] = input
  .shift()
  .trim()
  .split(" ")
  .map((item) => +item);
const  map = new Array(n).fill([]);
const visited = new Array(n).fill().map(i=>new Array(m).fill(Infinity)); 
for (let i = 0; i < n; i++) {
  map[i] = input
    .shift()
    .trim()
    .split("")
    .map((item) => +item);
}

bfs();
console.log(answer);
