---
title:  "2023-05-02 프로그래머스 연속된 부분 수열의 합"
excerpt: "프로그래머스 알고리즘 문제의 풀이입니다."

tags:
  - [Blog, algorithm, programmers]

toc: true
toc_sticky: true
 
date: 2023-05-02
last_modified_at: 2023-05-02

---

# 문제 풀이 방식

- 투 포인터 기법을 사용한다.
	- **두 개의 인덱스**만 탐색하면 OK
	- R부터 한칸씩 움직여서 원하는 값 되거나 그 값 넘기 전까지 인덱스 더함
	- 값 성립 -> L, R 값 저장
	- 값 넘어감 -> L 인덱스 값 삭제하고 L 한칸 오른쪽으로 움직임

# 문제 풀이 (Java) 

```
import java.util.Arrays;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int left = 0, right = -1, sum = 0;
        int length = 1000001, sLeft = 0, sRight = 0; 

        while (right < sequence.length) {
            if (sum < k) { // 부분 수열 합이 k보다 작다면
                if (++right < sequence.length) 
                    sum += sequence[right]; 
            } else if (k < sum) { // 부분 수열 합이 k를 넘어감
                sum -= sequence[left++]; 
            } else { // 합이 k임
                if (right - left < length) { 
                    length = right - left;
                    sLeft = left;
                    sRight = right;
                }
                sum -= sequence[left++];
            }
        }
        return new int[] { sLeft, sRight };
    }
}
```

