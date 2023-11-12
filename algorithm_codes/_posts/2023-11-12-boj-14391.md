---
title: "BOJ 14391 종이조각"
excerpt: "백준 14391 문제의 풀이입니다."
tags: DFS
toc: true
toc_sticky: true
date: 2023-11-12
last_modified_at: 2023-11-12
---

# 문제 풀이 방식
- 전형적인 DFS문제
	- 주의점: row, col 처리
- 완전탐색, 모든 조합에 대한 경우의 수 계산해야 하므로 `visited[][]` 필요
- DFS 말고 [비트마스킹](https://code-lab1.tistory.com/101)으로도 동일한 연산을 처리할 수 있다.
	
# 문제 풀이 (Java) 

```
import java.io.BufferedReader;  
import java.io.InputStreamReader;  
import java.util.StringTokenizer;  
  
public class boj14391 {  
    static int N, M;  
    static int[][] mat;  
    static int res = Integer.MIN_VALUE;  
    static boolean[][] visited;  
  
    static StringBuilder sb = new StringBuilder();  
    public static void main(String[] args) throws Exception {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        StringTokenizer st = new StringTokenizer(br.readLine());  
  
        N = Integer.parseInt(st.nextToken());  
        M = Integer.parseInt(st.nextToken());  
  
        mat = new int[N][M];  
        visited = new boolean[N][M];  
  
        int idx = 0;  
        for(int i = 0 ; i < N ; i++){  
            st = new StringTokenizer(br.readLine());  
            String tmp = st.nextToken();  
            for(int j = 0 ; j < M ; j++){  
                mat[i][j] = Character.getNumericValue(tmp.charAt(j));  
            }  
        }  
  
        DFS(0, 0);  
        System.out.println(res);  
    }  
  
    static void DFS(int row, int col){  
        if(row == N){ // 종료 조건  
            calc();  
            return;  
        }  
  
        // 한 줄 넘어가면 이에 대한 처리해줌  
        if(col >= M) {  
            DFS(row + 1, 0);  
            return ;  
        }  
  
        // 해당 행 방문한 경우 처리  
        visited[row][col] = true;  
        DFS(row, col + 1);  
  
        // 해당 행 방문 안한 경우 처리 ( 이경우 열로 계산함 )        visited[row][col] = false;  
        DFS(row, col + 1);  
    }  
  
    static void calc() {  
        int tmpres = 0;  
        int sumtmp = 0;  
  
        // 가로(행) 계산  
        for(int i=0;i<N;i++) {  
            sumtmp = 0;  
            for(int j=0;j<M;j++) {  
                if(visited[i][j]) { // 방문한 경우  
                    sumtmp *= 10;  
                    sumtmp += mat[i][j];  
                }else {  
                    tmpres += sumtmp;  
                    sumtmp = 0;  
                }  
            }  
            // 행 계산 끝날때마다 더해줌  
            tmpres += sumtmp;  
        }  
  
        // 세로(열) 계산  
        for(int i=0;i<M;i++) {  
            sumtmp = 0;  
            for(int j=0;j<N;j++) { // 세로로 순회  
                if(!visited[j][i]) {  
                    sumtmp *= 10;  
                    sumtmp += mat[j][i];  
                }else {  
                    tmpres += sumtmp;  
                    sumtmp = 0;  
                }  
            }  
            tmpres += sumtmp;  
        }  
  
        res = Math.max(res,  tmpres);  
    }  
  
}
```

