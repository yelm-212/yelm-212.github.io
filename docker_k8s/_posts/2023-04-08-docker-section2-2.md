---
layout: post
title:  "[Docker] Section 2 - docker 이미지 & 컨테이너 (2)"
excerpt: "udemy Docker & Kubernetes 강의를 듣고 정리한 내용입니다."

tags:
  - [Blog, docker, image, container]

toc: true
toc_sticky: true
 
date: 2023-04-08
last_modified_at: 2023-04-08

---

# 이미지와 컨테이너 관리

- `--help`명령어를 사용해서 사용 가능한 옵션이나 사용 방법을 확인할 수 있다.

## 컨테이너 중지 및 재시작

- `docker --help` : 도커로 실행 가능한 메인 명령어 목록을 얻을 수 있음
- `docker ps --help` : `docker ps`로 실행 가능한 모든 명령어 목록을 얻을 수 있음
	- `docker ps -a` : 모든 컨테이너 표시

> 새 컨테이너를 항상 `docker run` 으로 실행할 필요는 없다.
- `docker start (containerId)` : 변경 사항 없을 경우 기존 컨테이너를 다시 시작할 수 있음

## Attatched & Detached Container

- `docker run` 
	- attached 모드가 디폴트
	- container의 출력 결과를 **수신함**을 의미함
	- -d 옵션을 주어서 detached 모드로 실행할 수 있다.
- `docker start` 
	- detached 모드가 디폴트
	- container의 출력 결과를 **수신하지 않음**을 의미함
-  `docker logs` 
	- 컨테이너에 출력된 과거 로그를 확인할 수 있다.

## 인터렉티브 모드로 들어가기

```
// dockerfile
FROM python  

WORKDIR /app

COPY . /app

CMD ["python", "rng.py"]
```

-  `docker -i` 
	- 컨테이너에서 표준 입출력을 열린 상태로 유지한다.
- `docker -t` 
	- 컨테이너에서 터미널을 얻게 된다.
- `docker start -a` 
	- attatched 옵션으로 기존 도커 이미지를 실행한다

## 이미지와 컨테이너 삭제하기

- `docker rm (container_name)` 
	- 중지된 컨테이너를 삭제할 수 있다.
- `docker rmi (image_Id)` 
	- 이미지를 제거할 수 있다.
	- 컨테이너 삭제를 우선시하는것을 필요로 한다.

## 컨테이너를 자동으로 삭제하기

- `docker run --rm` 
	- 컨테이너를 중지시키면 자동으로 삭제하는 명령어이다.

## 이미지 검사하기
![](/attatchments/Screenshot 2023-04-11 at 7.12.02 PM.png)
![](/attatchments/Screenshot 2023-04-11 at 7.13.43 PM.png)
- `docker image inspect IMAGE_ID` 
	-  이미지에 대한 다양한 정보들을 확인할 수 있다,

## 컨테이너 파일 복사하기

- `docker cp LOCAL_SOURCE DEST_CONTAINER:/DEST_FOLDER`
- `docker cp SOURCE_CONTAINER:/SOURCE_FOLDER DEST_SOURCE`
- 위와 같은 명령어를 사용해 컨테이너 파일을 로컬로, 혹은 로컬 파일을 컨테이너로 복사할 수 있다.

## 컨테이너와 이미지에 이름, 태그를 지정하기

- 지정하지 않으면 tag, name은 지정되지 않거나 자동으로 지정된다.
- `docker --name NAME` 로 컨테이너의 이름을 지정해 줄 수 있다.
- 이미지 태그는 name : tag로 구성된다.
	- name은 보다 포괄적인 그룹을 나타내기 위해 사용한다. (node)
	- docker hub에 버전 등을 알려주기 위해 tag를 사용할 수 있다. (14)
	- `docker bulid -t NAME:TAG`를 통해 NAME, TAG를 지정할 수 있다.

# 이미지 공유

- 직접적으로 Dockerfile을 공유하지 않고도 빌드한 이미지를 사용해 동등한 환경을 구성할 수 있다.
- docker hub / private registry를 통해 이 이미지를 공유 가능하다.

- `docker push IMAGE_NAME` 로 공유
- `docker pull IMAGE_NAME` 로 사용

## Dockerhub에 이미지 push

1. `docker login`으로 로그인을 실행한다.
2. `docker push IMAGE_NAME:TAG` 명령어로 push한다.
3. hub에 업로드된 것을 확인할 수 있디.
![](/attatchments/Screenshot 2023-04-11 at 8.15.58 PM.png)

## 공유 이미지 pull

- `docker pull IMAGE_NAME`으로 생성한 이미지를 가져올 수 있다.
	- 로컬에서 실행할 때 처럼 다양한 옵션을 줘서 이미지를 실행 가능하다.