---
title:  "[Docker] Section 4 - 컨테이너 통신"
excerpt: "udemy Docker & Kubernetes 강의를 듣고 정리한 내용입니다."

tags:
  - [Blog, docker]

toc: true
toc_sticky: true
 
date: 2023-04-25
last_modified_at: 2023-04-25

---

# 컨테이너와 통신

## 컨테이너에서 WWW 통신하기

- 컨테이너 내부에서 외부로 API 요청을 할 수 있다.

## 컨테이너에서 로컬 호스트 머신으로 통신

- 컨테이너 내부의 데이터베이스 뿐만 아니라, 외부에 존재하는 데이터베이스(로컬 호스트 머신에 위치)와 통신할 수 있다.
- 이를 통해, 컨테이너 외부 DB 환경과 연결해 통신을 할 수 있다.
- 이를 위해 로컬 호스트 address를 컨테이너가 이해할 수 있는 URL으로 변경하는 과정이 필요하다.
	- `localhost` 대신 `host.docker.internal` 도메인을 사용한다.

## 다른 컨테이너와 통신

- 다른 컨테이너와도 통신이 가능하다.
- 실제로 데이터베이스와 실행되는 컨테이너를 분리하는 것이 권장된다.
- `docker container inspect mongodb` 를 통해 해당 컨테이너로 접속 가능한 IP 주소를 확인할 수 있다.

### 컨테이너 네트워크 형성

- 각기 다른 목적을 가지는 컨테이너 간 상호 통신이 가능하다
- `docker network create [NETWORK_NAME]`을 통해 도커 네트워크를 생성할 수 있다.
- `--network [NETWORK_NAME]` 옵션을 통해 생성된 네트워크를 사용할 수 있다.
- ip 주소를 사용하지 않고 도커 이름을 도메인으로 사용하더라도 자동으로 resolve된다.