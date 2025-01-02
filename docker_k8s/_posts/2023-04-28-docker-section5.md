---
title:  "[Docker] Section 5 - 다중 컨테이너 어플리케이션 구축"
excerpt: "udemy Docker & Kubernetes 강의를 듣고 정리한 내용입니다."

tags:
  - [Blog, docker]

toc: true
toc_sticky: true
 
date: 2023-04-28
last_modified_at: 2023-04-28

---

# 다중 컨테이너 어플리케이션 구축

- 여러 어플리케이션들로 구성된, 보다 현실적인 환경을 구축할 수 있다.
- 프론트엔드, 백엔드, 데이터베이스 영역을 구분하여 도커화할 수 있다.
	- DB, 백엔드 영역 데이터의 영속성이 유지되어야 한다.
	- 프론트엔드, 백엔드 영역에서 소스 코드 변경시마다 실시간 적용이 가능해야 한다.
	- DB에서 접근을 제한할 수 있어야 한다.

## 도커화된 백엔드와 로컬 호스트의 DB 통신

- `host.docker.internal` : 컨테이너 내부가 아닌 실제 로컬 호스트 머신의 IP를 통해 접근을 가능하게 해 준다.

## 도커화된 백엔드와 로컬 호스트의 프론트엔드 통신

- 도커 컨테이너 실행시 `-p PORT_NUM:PORT_NUM` 옵션을 통해 포트 번호를 외부에 게시해야 한다.

## 프론트엔드(React) 도커화하기

- `CMD ["npm", "start"]` : `package.json` 파일의 `start`부분을 실행하게 된다.
- 포트 번호 (3000)를 사용한다.

## 컨테이너 간 통신을 위한 도커 네트워크 추가하기

- ` docker network create [NETWORK_NAME]` 명령어를 통해 임의의 도커 네트워크를 생성할 수 있다.
- `docker run --name mongodb --rm -d --network [NETWORK_NAME] mongo` 를 통해 DB 컨테이너를 네트워크에서 실행시킨다.
- 백엔드와 DB가 통신하는 영역이 더이상 로컬호스트가 아니므로, `host.docker.internal`을 `mongodb(DB container 이름)`으로 변경한다.
- 프론트엔드와 백엔드 통신하는 영역 또한 `--network` 옵션을 주고, 백엔드 영역의 포트 번호를 게시한다. 

## 볼륨으로 데이터베이스 영속성 추가하기

- 데이터 영속성을 추가하기 위해, 데이터베이스 컨테이너에 명명된 볼륨 옵션을 추가한다.
	- `docker run --name mongodb -v data:/data/db --rm -d --network goals-net mongo`
- 보안을 위해 환경 변수`-e` 옵션으로 패스워드와 USERID 옵션을 추가할 수 있다.
	- `-e MONGO_INITDB_ROOT_USERNAME=username`, `-e MONGO_INITDB_ROOT_PASSWORD=password`
	- 백엔드 영역에서 DB와 연결되는 부분에 설정한 아이디와 비밀번호를 통해 접근이 가능하도록 수정한다.
	- 환경 변수를 `Dockerfile`에 추가할 수 있다.
 `mongodb://${process.env.MONGODB_USERNAME}:${process.env.MONGODB_PASSWORD}@mongodb:27017/course-goals?authSource=admin`

## 백엔드 컨테이너의 볼륨, 바인드 마운트 및 폴리싱

- 로그 파일을 위해 명명된 볼륨을 컨테이너의 `app/logs`에 바인딩하여 사용한다.
	- `logs:/app/logs`
- 소스 코드에서 변경 사항이 생길 때마다 확인하기 위해 app 폴더의 모든 것을 로컬 호스트 디렉토리에 바인딩할 하나의 볼륨이 더 필요하다. (바인드 마운트)
	- `-v [local_path]:/app`
- 백엔드 영역의 코드가 변경될 때마다 자동으로 재시작하기 위해, `package.json` 파일에 `devDependencies`, `start script`를 추가한다.
	- `"devDependencies": {"nodemon": "^2.0.4"}`
	- `"start": "nodemon app.js"`

## 프론트엔드 컨테이너 라이브 소스 코드 업데이트하기 (바인드 마운트)

- 백엔드에서 바인드 마운트를 해주는 것과 동일하게 진행한다.
	- `-v /app/src` 옵션 추가
- React는 자동으로 업데이트하는 기능을 가지고 있으므로 추가 종속성은 필요하지 않다. 
- 