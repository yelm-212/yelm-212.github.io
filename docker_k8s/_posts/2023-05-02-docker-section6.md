---
title:  "[Docker] Section 6 - 다중 컨테이너 어플리케이션 오케스트레이션"
excerpt: "udemy Docker & Kubernetes 강의를 듣고 정리한 내용입니다."

tags:
  - [Blog, docker]

toc: true
toc_sticky: true
 
date: 2023-05-02
last_modified_at: 2023-05-02

---

# 다중 컨테이너 어플리케이션 오케스트레이션

- 이전 다중 컨테이너 어플리케이션에서는 환경 구축을 위해서는 많은 설정을 거쳐야 했으나, 보다 쉽게 이러한 구성(Compose)를 할 수 있다.

## `Docker Compose`

- 이전 섹션에서 배웠던 다양한 명령어들을 한 파일에서 구성(Configure) 및 관리할 수 있다.
- Dockerfile, 이미지나 컨테이너 등을 대체하는 이미지가 아니다.
- 여러 호스트에 운용되는 다중 컨테이너 환경에서는 적합하지 않다. 
	- (동일 호스트에서 관리되어야 한다.)
- 거의 모든 터미널 명령어를 이 곳에서 관리할 수 있다.

### 파일 생성하기

- `docker-compose.yaml` 파일에 다중 컨테이너의 환경, 프로젝트 설정 등을 기재한다.
	- 항상 docker version을 기재하는 것으로 시작한다.
	- [공식 문서](https://docs.docker.com/compose/compose-file/compose-versioning/)에서 각 버전에 대응하는 다양한 옵션을 확인할 수 있다.
- `services` : 각 컨테이너에 대응된다.
	- `image` : 각 컨테이너에 사용하려는 이미지를 정의한다.
	- `build` : 컨테이너에서 사용할 이미지를 빌드할 위치를 지정한다. (Dockerfile의 위치)
		- dockerfile의 이름 및 위치를 정의하는 것 또한 가능하다.
	- `volume`:  컨테이너의 볼륨을 정의한다.
	- `environment` : 컨테이너의 환경 변수를 정의한다.
	- `env-file`: 환경 변수가 정의된 파일을 지정한다.(상대 경로)
	- `port` : 실행할 포트 번호를 지정할 수 있다.
	- `depends_on` : 각 컨테이너간 의존성을 명시할 수 있다.
	- `stdin_open` : 컨테이너가 입력을 받아들일 수 있게 한다.
	- `tty` : 표준 입출력을 터미널과 연결할 수 있도록 한다.
- 디폴트 네트워크가 자동 생성되므로, 네트워크를 정의할 필요가 없다. (필요시 옵션을 주어 지정하는 것 또한 가능하다.)
- Compose로 정의된 컨테이너는 도커에서는 자동 생성된 이름을 가지며, 서비스에서 정의된 이름으로 도커에 번역되어 전달될 수 있다.

## Compose file 실행 및 중지

- `docker-compose up` : docker compose 파일에 정의된 모든 작업들이 수행된다. (디폴트로 attached로 실행, -d 옵션 가능)
- `docker-compose down` : up 명령어로 실행 중이던 도커를 중지한다.

