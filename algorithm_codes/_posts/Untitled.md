---
title:  "BOJ 2342 Dance Dance Revolution(Java)"                      
excerpt: "백준 2342 문제의 풀이입니다."

tags:
  - [Blog, algorithm, BOJ, DP]

toc: true
toc_sticky: true
 
date: 2025-04-08
last_modified_at: 2025-04-08

---

# 문제 탐색하기
![](https://www.acmicpc.net/JudgeOnline/upload/201011/ddr.PNG)

- 처음에 게이머는 두 발을 중앙에 모으고 있다.(그림에서 **0의 위치**) 그리고 게임이 시작하면, 지시에 따라 왼쪽 또는 오른쪽 발을 움직인다. 하지만 그의 두 발이 동시에 움직이지는 않는다.
- 두 발이 같은 지점에 있는 것이 허락되지 않는다.
	- 한 발이 1의 위치에 있고, 다른 한 발이 3의 위치에 있을 때, 3을 연속으로 눌러야 한다면, 3의 위치에 있는 발로 반복해야 눌러야 한다.
- **발이 움직이는 위치에 따라서 드는 힘이 다르다**는 것을 알게 되었다. 만약, **중앙에 있던 발이 다른 지점으로** 움직일 때, **2**의 힘을 사용하게 된다. 
- **다른 지점에서 인접한 지점으로** 움직일 때는 **3**의 힘을 사용하게 된다. (예를 들면 왼쪽에서 위나 아래로 이동할 때의 이야기이다.) 
- **반대편**으로 움직일때는 **4**의 힘을 사용하게 된다. (위쪽에서 아래쪽으로, 또는 오른쪽에서 왼쪽으로). 
- 만약 **같은 지점을 한번 더** 누른다면, 그때는 **1**의 힘을 사용하게 된다.

# 코드 설계하기

## 구현 방법

- 3차원 배열을 이용해 DP를 적용하자. (메모이제이션)
- 각 발판에 드는 힘을 2차원 배열 형태로 저장한다. (`width[i][j]` : i발판에서 j발판으로 이동할 때 드는 힘)
- 재귀 호출을 통해 비용을 계산하고, 해당 순서 위치에서의 최솟값을 저장한다.

## 시간 복잡도

- 입력 길이를 `n`이라고 할 때, 총 상태의 수는 `n×5×5` => `O(n)`
	- n의 최댓값이 100,000 이어서 시간 내에 충분히 연산 가능하다.
- 결과는 DP값 출력해서 `O(1)`

# 정답 코드 (Java)

```java
import java.io.*;
import java.util.*;

public class Main {
    // DP[i][j][k]
    // i : 현재 발판 인덱스
    // j : 왼발 현재 발판
    // k : 오른발 현재 발판
    static int[][][] DP;
    static ArrayList<Integer> list;	// 밟는 발판 순서 저장
    static int size;

    // 각 발판 이동할 때 드는 힘 저장 배열
    static int[][] width = {
            {1, 2, 2, 2, 2},
            {0, 1, 3, 4, 3},
            {0, 3, 1, 3, 4},
            {0, 4, 3, 1, 3},
            {0, 3, 4, 3, 1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();

        while (true) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 0)
                break;
            list.add(n);
        }
        size = list.size();
        DP = new int[size][5][5]; // 재귀를 이용한 탐색 진행
        System.out.println(search(0, 0, 0));	// 최소 힘 출력

    }

    private static int search(int idx, int l, int r) {
        if(idx == size)	// 모두 밟음
            return 0;

        if(DP[idx][l][r] != 0)	// 이미 밟아본 발판일 경우
            return DP[idx][l][r];

        int nxt = list.get(idx); // 다음 발판
        // search(idx+1, nxt, r) + width[l][nxt]) 왼발로 다음 발판 밟는 경우
        // search(idx+1, l, nxt) + width[r][nxt]) 오른발로 다음 발판 밟는 경우
        DP[idx][l][r] = Math.min(search(idx+1, nxt, r) + width[l][nxt],  search(idx+1, l, nxt) + width[r][nxt]);

        return DP[idx][l][r];
    }
}
```

