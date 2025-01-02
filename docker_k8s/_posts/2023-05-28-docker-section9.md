---
title:  "[Docker] Section 9 - 도커 컨테이너 배포"
excerpt: "udemy Docker & Kubernetes 강의를 듣고 정리한 내용입니다."

tags:
  - [Blog, docker]

toc: true
toc_sticky: true
 
date: 2023-05-28
last_modified_at: 2023-05-28

---

# AWS 서비스 사용

## EC2 서비스 사용하기

- EC2를 사용해 개인 리모트 머신을 사용해 배포할 수 있다.
- 기존 방식처럼 소스코드 전체를 리모트 머신에 복사해 배포할 수도 있지만, 도커를 사용하면 로컬에서 생성한 이미지를 클라우드에 push해 배포할 수 있다.