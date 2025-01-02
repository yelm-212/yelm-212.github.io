---
title:  "[Docker] Section 7 - 유틸리티 컨테이너로 작업하기"
excerpt: "udemy Docker & Kubernetes 강의를 듣고 정리한 내용입니다."

tags:
  - [Blog, docker]

toc: true
toc_sticky: true
 
date: 2023-05-18
last_modified_at: 2023-05-18

---

# 유틸리티 컨테이너로 작업하기 

- 특정 환경만 포함하는 컨테이너를 유틸리티 컨테이너라 한다. 
- custom command를 실행하거나 추가한다.

- 기존에는 초기 종속성을 설정하기 위해 nodeJs와 같은 어플리케이션들의 설치가 필요하였다.
- 이 부분을 유틸리티 컨테이너로 개선할 수 있다.

## 컨테이너에서 명령을 실행하는 다양한 방법

- `docker exec [dokcername]` 명령어를 통해 이미 실행중인 컨테이너에 명령을 수행하라고 지시할 수 있다.
- `npm init`에 디폴트 명령어를 오버라이드 해 사용할 수도 있다.

## 유틸리티 컨테이너 구축

- 사용자가 이미지에 대한 모든 명령을 실행할 수 읶세 하기 위해 -t node-util 옵션을 줄 수 있다
- 호스트 머신에 부가 도구를 설치하지 않고도 호스트 머신에 영향을 끼치기 하기 위해 유틸리티 옵션을 줄 수 있다.

## ENTRYPOINT 활용

- dockerfile에 `ENTRYPOINT` 명령어를 추가하여, `docker run`에서 이미지 이름 뒤에 입력하는 명령어가 `ENTRYPOINT`의 명령어의 뒤에 실행되도록 할 수 있다.

## Docker Compose 사용

- `docker-compose.yaml`설정들을 정의하여 사용할수 있다.
	- stdin_open, tty, volume 등의 옵션을 사용할 수 있다.
	- 이러한 compose 명령어를 사용하면, 보다 유지하기 편리하며 매번 명령어들을 입력할 필요가 없어진다.
	- `docker-compose run` 명령어로 실행하여야 한다. (이 뒤에 npm과 같은기타 옵션을 줄 수도 있다)
	- up, down 명령어를 사용하지 않는다.(자동 실행 및 종료)
