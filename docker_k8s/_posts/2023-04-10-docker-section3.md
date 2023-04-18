---
title:  "[Docker] Section 3 - 데이터 관리 및 볼륨으로 작업하기 "
excerpt: "udemy Docker & Kubernetes 강의를 듣고 정리한 내용입니다."

tags:
  - [Blog, docker, volume]

toc: true
toc_sticky: true
 
date: 2023-04-10
last_modified_at: 2023-04-18

---

도커 내에서 다양한 데이터의 유형을 다룰 수 있다.

|        | 임시적인 앱 데이터 | 영구적인 앱 데이터 | 어플리케이션 데이터 |
|--------|----------------------|------------------------|--------------------------|
| 쓰기 가능 여부 | 가능 | 가능 | 불가 |
| 읽기 가능 여부 | 가능 | 가능 | 읽기만 가능 |
| 데이터 보존 여부 | 컨테이너가 중지/재실행되면 사라짐 | 파일 혹은 데이터베이스에 저장되어 영구적으로 보존됨 | - |

# 데모 앱 구축하기

Dockerfile
```
FROM node:14

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 80

CMD [ "node", "server.js" ]
```

1. `docker build -t feedback-node .` 로 이미지를 생성한다.
2. `docker run -p 3000:80 -d --name feedback-app --rm feedback-node` 로 컨테이너를 생성한다.

- 구축된 컨테이너를 중지 후 재실행해도 feedback file은 그대로 남아있는 것을 확인할 수 있다.
- 컨테이너가 삭제되더라도 데이터를 유지하고 싶다면? > Volume 사용!

## 볼륨

- 컨테이너 내부의 폴더를 호스트 머신 상의 컨테이너 폴더에 추가할 수 있다.
- 이를 통해 데이터를 지속시킬 수 있다.
- `VOLUME [ "/app/feedback" ]`을 Dockerfile에 추가한다.
- `docker run -d -p 3000:80 --rm --name feedback-app feedback-node:volumes` 명령어를 터미널에 실행한다.
- 볼륨이 추가되어 중지 후 재실행하더라도 제출한 파일이 남아있는 것을 확인할 수 있다.

> 지금까지의 진행한 내용은 `docker volume` 커맨드로 확인 가능하다.

- 볼륨을 개발자가 로컬에서 관리할 필요가 없다면, named volume이 적합하다.
- 볼륨을 개발자가 로컬에서 관리하고 싶다면, named volume이 적합하다.
- `docker run -d -p 3000:80 --rm --name feedback-app -v feedback:/app/feedback feedback-node:volumes`  를 통해 볼륨에 이름을 지정해 줄 수 있다.
- `docker volume prune` 을 통해 사용하지 않는 익명 볼륨을 제거할 수 있다.

# 바인드 마운트

- 도커에 의해 관리되는 폴더와 달리, 바인드 마운트는 유저가 사용하는 호스트 머신의 경로를 지정해 사용할 수 있다.
- 소스코드를 넣을 수도 있다.
- 터미널에서 명령어로 설정해 주어야 한다. `/LOCAL_PATH`
	- 절대 경로, 상대 경로 모두 가능하다.
	- `-v $(pwd):/app` 명령어를 전체 경로의 shortcut으로 사용할 수 있다.
- 도커가 호스트 머신 내 경로에 접근이 가능하도록 설정해 주어야 한다.

## Nodemon 사용하기

- 개발 중 변경 사항을 바로 반영할 수 있다.
- package.json에 의존성과 script로 nodemon을 추가한다.

## 읽기 전용 볼륨

경로 뒤에 `:ro` 명령어를 추가한다.

## 볼륨 관리

- `docker volume` 명령어를 통해 볼륨을 관리할 수 있다.
- `docker volume prune` 명령어를 통해 사용중이지 않은 볼륨을 일괄 삭제할 수 있다.

## `.dockerignore` file

- dockerfile에서 전체 복사시 포함하지 않을 파일을 지정하는 용도로 사용한다.
- 기본적으로 `node_modules` 가 포함되어, 해당 폴더의 내용이 이미지에 복사되지 않도록 한다.
- `.git`, `Dockerfile` 등을 추가할 수 있다.

## 인수와 환경 변수

- 이미지 빌드 시 `--build-arg`인수를 사용하면, 도커파일 내에서 설정할 수 있다.
- 도커 실행시 `--env` 환경 변수를 사용하면 도커파일과 어플리케이션 코드 내에서 모두 사용할 수 있다.
- 두가지 모두 사용시 , 사용하지하지 않을 때의 코드의 하드코딩을 줄일 수 있다.

### 환경 변수 설정

- Dockerfile에 `ENV (VAR) (VAULE)` 명령어를 추가하여 설정할 수 있다.
- `EXPOSE $(VAR)` 명령어로 추가하여 환경 변수로 선언함을 알려야 한다.
- 실행시 `VAR`을 재할당 할 수 있다.
- `.env` 파일에 정의할 수도 있다.

### 인수 설정

- Dockerfile에 `ARG (VAR)=(VAULE)` 명령어를 추가하여 설정할 수 있다.
- Dockerfile 내에서, 명령어에 사용할 수 있다.
- 빌드시 재할당이 가능하다.

