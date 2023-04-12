---
layout: post
title:  "[Docker] Section 3 - 데이터 관리 및 볼륨으로 작업하기 "
excerpt: "udemy Docker & Kubernetes 강의를 듣고 정리한 내용입니다."

tags:
  - [Blog, docker, volume]

toc: true
toc_sticky: true
 
date: 2023-04-10
last_modified_at: 2023-04-10

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