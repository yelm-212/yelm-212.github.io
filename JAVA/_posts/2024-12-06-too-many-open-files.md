---
title: "Jenkins Too Many Open Files"
excerpt: "Jenkins Too Many Open Files error"
categories:
  - Blog
tags: 
toc: true
toc_sticky: true
date: 2024-12-06
last_modified_at: 2024-12-06
---

## Jenkins Too Many Open Files 메시지

리눅수 환경에서 FD 기본 설정된 개수 초과해서 발생한다.
초기 설정값은 soft - 2048 hard - 4096 이다.

```bash
cat /proc/sys/fs/file-max
```

로 최대 개수를 확인하자. 이 값보다는 설정하려는 FD 개수가 작아야 한다.

```bash
vi /etc/security/limits.conf
```
저 파일 맨 밑에

```bash
* soft nofile [설정할 개수]
* hard nofile [설정할 개수]
```

를 추가해주면 된다. 파일 내에 컨벤션 가이드 들어있어서 그거 따라서 해주면 된다.

그리고 나서 **로그아웃했다가 다시 ssh 접속해주면** `prlimit -n` 했을때 설정한 값이 뜬다.



