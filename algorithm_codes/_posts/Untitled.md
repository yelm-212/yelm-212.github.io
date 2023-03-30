---
title:  "2023-03-30 BOJ"
excerpt: "description"

tags:
  - [Blog, algorithm, BOJ]

toc: true
toc_sticky: true
 
date: 2023-03-30
last_modified_at: 2023-03-30

---

# 문제 풀이 방식
	
# 문제 풀이 (Java) 

```
// 처음에 재귀 호출 횟수 오류난 코드
public class Solution {
	public int[] reverseArr(int[] arr){
		if (arr.length == 0 || arr.length == 1) return arr;
		int[] newArr = Arrays.copyOf(arr, arr.length);
		reverse(newArr, 0, newArr.length -1);
		return newArr;

	}

  

	void swap(int[] arr, int i, int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	void reverse(int[] arr, int low, int high){
		if(low < high){
		swap(arr, low, high);
		reverse(arr, low + 1, high - 1);
		}
	}

}
```