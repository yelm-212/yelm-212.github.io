---
title:  "[Java] - 기본 알고리즘"
excerpt: "자주 언급되는 알고리즘에 대해 학습한 내용을 정리하였습니다."

tags:
  - [Blog, CS, algorithm, sorting-algorithm, binary-search, MST, prim, kruscal, bellmanFord, dijkstra]

toc: true
toc_sticky: true
 
date: 2023-03-26
last_modified_at: 2023-03-26

---
# 정렬 알고리즘

알파벳 순으로, 혹은 오름차순/ 내림차순으로 정렬하고자 할떼 사용한다. 이진 탐색을 수행하기 위해서는 정렬이 된 배열이어야 한다.

## 선택 정렬, 버블 정렬, 삽입 정렬

- 위의 정렬은 모두 $O(n^2)$ 의 시간 복잡도를 가진다.

### 선택 정렬

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F161DC34E4F3647EC21)

- 선택 정렬은 가장 앞의 노드를 기준으로 뒤로 가면서 값을 비교하는 연산을 수행한다.

### 버블 정렬

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F14052B484F36481D2B)

- 버블 정렬은 자신과 인접한 노드와 값을 비교하여 정렬한다

### 삽입 정렬

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F192B0C504F3646BC13)

- 해당되는 슬롯의 앞에 존재하는 모든 값과 비교하여 정렬하는 방식이다.


## 병합 정렬, 퀵 정렬, 힙 정렬 

|           | 병합 정렬   | 퀵 정렬   | 힙 정렬    |
|-----------|------------|------------|------------|
| 시간 복잡도 | O(n log n) | O(n log n) | O(n log n) |
| 안정성    | 안정적      | 불안정적    | 불안정적    |
| 공간 복잡도 | O(n)       | O(log n)   | O(1)       |
| 평균 속도   | 중간        | 빠름        | 느림        |


### 병합 정렬 (Merge Sort)

-   분할 정복 방식을 사용한 정렬 알고리즘
-   배열을 두 부분으로 분할하고, 각 부분을 재귀적으로 병합하면서 정렬하는 방식
-   시간 복잡도: $O(n log n)$
-   안정적인 정렬 방식(stable, in-place)

### 퀵 정렬 (Quick Sort)

-   분할 정복 방식을 사용한 정렬 알고리즘
-   피벗(pivot)을 기준으로 작은 값은 왼쪽, 큰 값은 오른쪽으로 나누어 정렬하는 방식
-   피벗을 선택하는 방법에 따라 성능이 달라질 수 있음
-   최악의 경우 시간 복잡도가 $O(n^2)$이 될 수 있으나, 일반적으로 평균적으로 $O(n log n)$의 성능을 보임
-   불안정적인 정렬 방식 (not stable)

### 힙 정렬 (Heap Sort)

-   완전 이진 트리 구조를 사용하여 정렬하는 방식
-   최대 힙 혹은 최소 힙 구조를 유지하면서, 루트 노드를 맨 뒤로 이동시키고 힙 크기를 1씩 감소시키면서 정렬하는 방식
-   시간 복잡도: $O(n log n)$
-   불안정적인 정렬 방식 (not stable)

# 이분 탐색

![](https://upload.wikimedia.org/wikipedia/commons/8/83/Binary_Search_Depiction.svg)

- 정렬된 리스트에서 특정한 값을 찾는 알고리즘
- 리스트의 중간값과 비교해 찾는 값이 중간값보다 작으면 왼쪽 부분 리스트에서, 크면 오른쪽 부분 리스트에서 찾는 방법을 반복하는 알고리즘

## 동작 방식

1.  리스트의 가운데 인덱스를 찾는다.
2.  찾고자 하는 값과 중간값을 비교한다.
3.  찾고자 하는 값이 중간값보다 작으면, 리스트의 왼쪽 부분에서 다시 탐색한다.
4.  찾고자 하는 값이 중간값보다 크면, 리스트의 오른쪽 부분에서 다시 탐색한다.
5.  찾고자 하는 값이 중간값과 같다면 탐색을 종료한다.

위의 과정을 반복하여 찾고자 하는 값이 리스트에 존재하는지 찾을 수 있다.

시간 복잡도는 $O(logn)$이다.

## 예시 코드

```
public static int binarySearch(int[] arr, int target) {
    int start = 0;
    int end = arr.length - 1;

    while (start <= end) {
        int mid = (start + end) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
    }

    return -1; // target이 리스트에 없을 때
}

```

# 동적계획법

- 하나의 문제를 여러 개의 하위 문제로 나누어 푸는 알고리즘
- 하위 문제들을 한 번만 풀고, 그 결과를 토대로 전체 문제를 푸는 방식

- 메모리에 계산한 값을 저장해 두었다가 이후에 같은 계산이 필요할 때 저장된 값을 불러와 재활용함으로써 중복 계산을 피하는 효율적인 방법
	- 이를 통해 시간 복잡도를 크게 줄일 수 있다.

- 탑다운(Top-Down) 
	- 재귀 함수를 이용하여 하위 문제를 호출하는 방식
- 바텀업(Bottom-Up)
	- 반복문을 이용하여 작은 하위 문제부터 차례대로 해결해 나가는 방식

## 예시 코드

```
public static int fibonacci(int n) {
    if (n <= 1) {
        return n;
    }

    int[] memo = new int[n + 1];
    memo[0] = 0;
    memo[1] = 1;

    for (int i = 2; i <= n; i++) {
        memo[i] = memo[i - 1] + memo[i - 2];
    }

    return memo[n];
}

```

# 최단 경로

- 두 지점 간에 가장 짧은 경로를 찾는 문제
- 일반적으로 그래프 알고리즘에서 많이 다루어진다.

## 다익스트라 알고리즘

- 하나의 시작 정점에서 다른 모든 정점까지의 최단 경로를 찾는 알고리즘
- BFS를 이용한 방식
- 시간 복잡도 $O(n^2)$
-   음의 가중치가 없는 그래프에서만 사용 가능하다.
-   그래프 내에 사이클 불가
-   그리디 알고리즘으로 분류됨

### 동작 과정

1.  시작 정점을 기준으로 거리 값을 초기화한다. 시작 정점의 거리는 0, 나머지 정점의 거리는 무한대(INF)로 초기화한다.
2.  방문하지 않은 정점 중에서 출발점으로부터 가장 가까운 정점을 선택한다.
3.  해당 정점을 거쳐 다른 정점으로 가는 비용을 계산하고, 현재까지 구한 비용보다 더 적으면 최단 거리 값을 갱신한다
4.  모든 정점이 방문될 때까지 2~3 과정을 반복한다.

### 예시 코드

```
public static void dijkstra(int[][] graph, int start) {
    int n = graph.length;
    int[] distance = new int[n];   // 시작점에서 각 정점까지의 최단 거리
    boolean[] visited = new boolean[n];   // 방문한 정점 체크

    // distance 배열 초기화
    for (int i = 0; i < n; i++) {
        distance[i] = Integer.MAX_VALUE;
    }
    distance[start] = 0;

    // 최단 경로 탐색
    for (int i = 0; i < n - 1; i++) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;

        // 현재까지 발견한 가장 짧은 거리를 가진 정점을 찾음
        for (int j = 0; j < n; j++) {
            if (!visited[j] && distance[j] < minDistance) {
                minDistance = distance[j];
                minIndex = j;
            }
        }

        // 발견한 가장 짧은 거리를 가진 정점을 방문
        visited[minIndex] = true;

        // 현재 정점에서 인접한 정점들의 거리 갱신
        for (int j = 0; j < n; j++) {
            if (!visited[j] && graph[minIndex][j] != 0 && distance[minIndex] != Integer.MAX_VALUE
                    && distance[minIndex] + graph[minIndex][j] < distance[j]) {
                distance[j] = distance[minIndex] + graph[minIndex][j

```


## 벨만 포드 알고리즘

- 음의 가중치가 포함된 그래프에서 최단 경로를 찾는 알고리즘.

### 동작 과정
1.  시작 정점을 기준으로 거리 값을 초기화한다. 시작 정점의 거리는 0, 나머지 정점의 거리는 무한대(INF)로 초기화한다.
2.  간선을 하나씩 확인하면서, 시작 정점에서 간선을 통해 도달할 수 있는 정점까지의 거리를 계산한다.
3.  현재까지 구한 최단 거리보다 더 적은 거리를 구할 수 있다면, 최단 거리 값을 갱신한다.
4.  모든 간선에 대해 2~3 과정을 반복한다.
5.  음수 사이클이 있는지 확인한다. 음수 사이클이 존재한다면, 최단 거리 값을 구할 수 없다.

# 최소 비용(MST)

- 경로를 따라가며 드는 비용이 최소화되는 경로를 찾는 문제. 
- 일반적으로 그래프 알고리즘에서 많이 다루어진다.
- 모든 정점을 연결할 때 사이클을 형성하지 않아야 한다.

## 크루스칼 알고리즘

![](https://t1.daumcdn.net/cfile/tistory/232ACE3F570B903F30)

-  간선을 기준으로 트리를 만드는 방법
- 처음에는 모든 정점이 각각의 집합으로 구성되어 있고, 이를 하나씩 합쳐가면서 최소 신장 트리를 구한다.

### 동작 과정

1.  그래프의 모든 간선을 가중치에 따라 오름차순으로 정렬한다.
2.  정렬된 순서대로 간선을 선택하면서, 해당 간선의 양 끝 정점이 서로 다른 집합에 속하는지 확인한다.
3.  양 끝 정점이 서로 다른 집합에 속한다면, 해당 간선을 최소 신장 트리에 추가하고, 두 집합을 합친다.
4.  모든 간선에 대해 2~3 과정을 반복한다.

```
class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }
}

public static void kruskalMST(int[][] graph) {
    int n = graph.length;
    Edge[] edges = new Edge[n * (n - 1) / 2];
    int index = 0;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (graph[i][j] != 0) {
                edges[index++] = new Edge(i, j, graph[i][j]);
            }
        }
    }

    // 간선 가중치를 오름차순으로 정렬
    Arrays.sort(edges);

    int[] parent = new int[n];
    for (int i = 0; i < n; i++) {
        parent[i] = i;   // 처음에는 모든 정점이 각각의 집합
    }

    int selectedEdges = 0;
    index = 0;
    while (selectedEdges < n - 1) {   // n개의 정점을 가진 그래프의 최소 신장 트리는 n-1개의 간선으로 이루어짐
        Edge edge = edges[index++];
        int srcParent = findParent(parent, edge.src);
        int destParent = findParent(parent, edge.dest);
        if (srcParent != destParent) {   // 두 정점이 서로 다른 집합에 속할 때만 간선 추가
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
            parent[srcParent] = destParent;
            selectedEdges++;
       

```

## 프림 알고리즘

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F245DAD33570B93512D)

- 시작 정점에서부터 출발하여 신장 트리 집합을 점차적으로 확장해가는 방식으로 최소 신장 트리를 구하는 방식.

### 동작 과정

1.  임의의 정점을 시작 정점으로 선택한다.
2.  시작 정점과 연결된 간선들을 간선 리스트에 추가한다.
3.  간선 리스트에서 가장 작은 가중치를 가진 간선을 선택한다.
4.  선택한 간선의 연결된 정점을 방문한 적이 없다면, 해당 정점과 연결된 간선들을 간선 리스트에 추가한다.
5.  모든 정점을 방문할 때까지 3~4 과정을 반복한다.


``` 
public static void primMST(int[][] graph) {
    int n = graph.length;
    boolean[] visited = new boolean[n];
    int[] distance = new int[n];
    int[] parent = new int[n];

    // distance, parent 배열 초기화
    Arrays.fill(distance, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);

    distance[0] = 0;   // 시작점 선택

    // 모든 정점을 방문할 때까지 반복
    for (int i = 0; i < n - 1; i++) {
        // 최소 가중치의 간선 찾기
        int minDistance = Integer.MAX_VALUE;
        int minVertex = -1;
        for (int j = 0; j < n; j++) {
            if (!visited[j] && distance[j] < minDistance) {
                minDistance = distance[j];
                minVertex = j;
            }
        }

        visited[minVertex] = true;   // 해당 정점 방문 처리

        // 연결된 간선들을 간선 리스트에 추가
        for (int k = 0; k < n; k++) {
            if (graph[minVertex][k] != 0 && !visited[k] && graph[minVertex][k] < distance[k]) {
                distance[k] = graph[minVertex][k];
                parent[k] = minVertex;
            }
        }
    }

    // 결과 출력
    for (int i = 1; i < n; i++) {
        System.out.println(parent[i] + " - " + i + " : " + graph[parent[i]][i]);
    }
}

```