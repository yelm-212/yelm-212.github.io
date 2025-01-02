---
title:  "[Docker] Section 8 - Laravel, PHP 도커화 프로젝트"
excerpt: "udemy Docker & Kubernetes 강의를 듣고 정리한 내용입니다."

tags:
  - [Blog, docker]

toc: true
toc_sticky: true
 
date: 2023-05-22
last_modified_at: 2023-05-22

---

# 타겟 설정

- 도커를 사용하면, 호스트 머신에 아무것도 설치하지 않고도 Laravel 어플리케이션을 빌드할 수 있다.
- Docker compose - `docker-compose.yaml`  - 를 사용해 복잡한 유틸리티 컨테이너를 설정할 수 있다.

## 웹 서버 컨테이너

- 해당 프로젝트에서는 도커의 공식 nginx 이미지를 사용하였다.

## PHP 컨테이너

- 해당 프로젝트에서는 Laravel 연결을 위해 custom image를 만들어 사용하였다.
- 하위 폴더(`dockerfiles`) 내에서 정의한 `php.dockerfile`을 docker compose file에서 이미지 빌드시 사용할 수 있다. 
- 프로젝트 폴더 내에서 소스코드 작업을 위해 바인드 마운트를 설정할 수 있다.

## MySQL 컨테이너

- 해당 프로젝트에서는 도커의 공식 mysql 이미지를 사용하였다.
- 하위 폴더 `env` 내의 mysql.env 파일 내에서 여러가지 환경 변수를 설정할 수 있다. docker compose에서 사용하려면 이 파일의 경로를 지정하면 된다.

## Composer 컨테이너 

- 유틸리티 컨테이너
- 해당 프로젝트에서는 custom image를 만들어 사용하였다.
	- `composer.dockerfile`은 composer 이미지를 베이스 이미지로 사용한다.

> 여러개의 dockerfile을 설정해 compose할수도 있다. 

## 일부 docker compose 서비스만 구동하기

- docker-compose를 시행할 때 변경사항이 있을 경우, 이미지를 다시 생성하도록 강제하려면 실행 시 `--build` 옵션을 주면 된다.

## 유틸리티 컨테이너를 더 추가하기

- docker compose에서 entrypoint를 설정할 수도 있다.
- docker-compose에서 dockerfile은 선택사항이며, 실행하는 환경에 따라 알맞게 선택하면 된다.

## 바인드 마운트와 copy

두가지 모두 컨테이너를 실행할 때 파일 및 디렉토리를 컨테이너 내부에 공유하는 방법이다.

- 바인드 마운트는 호스트 시스템의 파일 또는 디렉토리를 컨테이너와 실시간으로 공유한다.
- 복사는 호스트 시스템의 파일이나 디렉토리를 컨테이너로 한 번 복사한다.
- 바인드 마운트는 변경 사항을 실시간으로 반영하고 호스트와 컨테이너 간의 파일 공유에 유용하다.
- 복사는 독립적인 파일 시스템을 제공하며 초기 데이터 제공 등에 사용된다.