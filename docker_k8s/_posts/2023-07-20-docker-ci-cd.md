---
title:  "[Docker] [GitHub Actions] [AWS EC2] Spring Boot 프로젝트의 CI, CD 그런데 이제 Docker를 곁들인..."
excerpt: "GitHub Actions, Docker, AWS를 사용해 CI CD 적용 과정 및 방법을 기술하였습니다."

tags:
  - [docker, docker-compose, AWS, GitHub_Actions, CI, CD]

toc: true
toc_sticky: true
 
date: 2023-07-20
last_modified_at: 2023-07-20

---

일단 내가 만든 CI/CD 구조가 어떻게 실행되는지  설명을 하자면, 내 GitHub repository에서 코드가 commit 될 때 마다 docker hub에 push ➡ 이 이미지(latest)를 EC2의 docker-compose에서 사용해서 docker container를 띄우는 방식이다.

# GitHub Actions 초기 세팅

일단 먼저 GitHub 의 CI/CD를 진행하고 싶은 프로젝트의 `Actions` 탭에서 `Java CI with Gradle` < 이것을 선택해주어야 하는데, 
선택해주면 프로젝트 루트 경로 이하에 `/.github/workflows/gradle`폴더가 생성된다.

![](/attatchments/20230720145323.png)

그리고 이 탭이 생겼는지 확인하면 된다.

# Secret key 등록하기

사실 GitHub에 새 탭이 생겨서 여기서 반 이상 헤맸는데...... 공식 문서에도 업데이트가 안 되어있었다. 알고보니 오른쪽 위에 있었는데 거의 6시간을 이거 찾느라 헤맸다. Settings > Security > secrets and variables > Actions 탭에서 오른쪽 위의 New Repository Secret 을 눌러서 보안이 적용되어야 하는 환경 변수들(비밀번호, 키 값 등...)을 넣어주면 된다.

![](/attatchments/2023-07-2003.09.09PM.png)

# `Dockerfile`, `docker-compose.yml` 파일 작성

```
# Dockerfile

FROM openjdk:11-jdk  
ARG JAR_FILE=build/libs/*.jar  
COPY ${JAR_FILE} app.jar  
ENV JAVA_OPTS="-Dcom.amazonaws.sdk.disableEc2Metadata=true"  
EXPOSE 8080    
ENTRYPOINT ["java","-jar","/app.jar"]

```

Spring Boot 프로젝트의 루트 경로(server)에 Dockerfile을 넣어주었다. IDE의 터미널에서 `docker build -t [username]/[repository name]:[tag] .`를 실행해, 초기 이미지를 빌드하는 것이 가능한지 확인한다. (생략가능)

```
version: '3'  
  
services:  
  
  application:  
    container_name: spring  
    image: 21yrshin/seb44_main_017:latest  
    expose:  
      - 8080  
    ports:  
      - 8080:8080  
    tty: true  
    stdin_open: true  
    environment:  
      SPRING_DATASOURCE_URL:  
      SPRING_DATASOURCE_USERNAME:  
      MYSQL_PASSWORD:   
      ... 등등 Spring Boot 프로젝트 실행을 위한 환경변수들
    networks:  
      - test_network  
  
networks:  
  test_network:  
    driver: bridge
```

EC2에 docker-compose를 설치하고, home directory(~)에 `docker-compose.yml` 파일을 작성해 주었다.
환경 변수 파일을 따로 작성해도 되지만, 나의 경우 ~~귀찮아서~~ 컨테이너 수가 적고 어차피 EC2에서만 관리할 파일이기 때문에 그냥 파일에 바로 작성했다.
컨테이너가 더 많아지거나 GitHub로 해당 파일을 관리할 예정이라면 .env 파일을 따로 작성하는 것이 좋을 것이다.

# `Gradle.yml` 파일 작성

```
name: Java CI with Gradle  
  
on:  
  push:  
    branches: [ "be-dev" ]  
  pull_request:  
    branches: [ "be-dev" ]  
  
permissions:  
  contents: read  
  
defaults:  
  run:  
    working-directory: server  
```

나는 루트 디렉토리가 아닌 루트 디렉토리 이하 `server` 폴더가 java spring boot 프로젝트의 루트 경로였기 때문에 `working-directory`를 별도로 지정해주었다. 

그리고 main 이 아닌 별도 branch(`be-dev`)를 트리거로 하고 싶었기에 이를 명시해주었다.

```
  
jobs:  
  build:  
    runs-on: ubuntu-latest  
  
    steps:  
      ## JDK setting  
  
      - name: Checkout  
        uses: actions/checkout@v3  
        with:  
          ref: be-dev  
          repository: [username]/[repository name]:[tag]
```

ref 로 한번 더 어떤 브랜치를 선택할 건지 명명해주었다.

```
  
      - name: Set up JDK 11  
        uses: actions/setup-java@v3  
        with:  
          java-version: '11'  
          distribution: 'temurin'  
  
      - name: Set permission for gradlew  
        run: chmod +x ./gradlew  
  
      ## Gradle Build  
      - name: Build with Gradle  
        run: ./gradlew bootJar  
```

`gradle build`를 사용하기 위해 JDK 버전 설정, 실행 권한을 부여한 후 빌드를 실행해주면 프로젝트 `.jar` 파일이 생성된다.

```
  
      ## Docker with Docker Setup Buildx  
  
      - name: Set up Docker Buildx  
        uses: docker/setup-buildx-action@v1  
  
      - name: Login to DockerHub  
        uses: docker/login-action@v1  
        with:  
          username: ${{ secrets.DOCKER_USERNAME }}  
          password: ${{ secrets.DOCKER_PASSWORD }}  
```

docker 이미지 빌드 및 push는 SSH에서 진행하지 않고, GitHub Actions의 [docker setup buildx](https://github.com/marketplace/actions/docker-setup-buildx)와 [build-push-action](https://github.com/marketplace/actions/build-and-push-docker-images)을 사용했다.

``` 
      ## push to docker hub  
      - name: web docker build and push with docker action  
        uses: docker/build-push-action@v1  
        with:  
          dockerfile: ./server/Dockerfile  
          username:  
          password: 
          path: server  
          push: true  
          repository: [username]/[repository name]
          tags: latest  
```

`working-directory`를 build-push-action에서는 인식 못하는 문제가 있어서 path, repository, tag, dockerfile 위치를 직접 명시해주었다.

![](workindirnotapplying.png)
해당 issue 글을 참고해 작성하였다.

근데 작동에는 문제가 전혀 없으나 build-push-action이 지난 버전인데 최신 버전에서는 적용이 안된다... 최신 버전에서 적용하는 방법 아시는 분 계시면 알려주시면 감사하겠습니다.

```
  
      ## docker compose up  
      - name: executing remote ssh commands using password  
        uses: appleboy/ssh-action@v0.1.4  
        with:  
          host: ${{ secrets.HOST }}  
          username: ubuntu  
          key: ${{ secrets.KEY }}  
          script: |  
            sudo docker rm -f spring  
            sudo docker pull [username]/[repository name]:[tag]  
            docker-compose up -d  
            docker image prune -f
```

AWS EC2에 SSH 연결하기 위한 값들을 동일하게 넣고 실행해주면 된다.

- HOST : SSH 로그인을 위한 IPv4 DNS 주소
- KEY : KEY값 (vim이나 cat 명령어로 확인 가능)

이와 같은 과정을 마치면 GitHub Actions 가 정상수행되는 것을 확인할 수 있다.

![](/attatchments/20230720155433.png)

EC2에서 실행된 컨테이너에 의해, 기존 Spring의 Http request들도 정상작동한다.

![](/attatchments/D9CA9AFB-2AB9-4C42-AE47-B727FFF91BCE_4_5005_c.jpeg)


## 참고한 글들

- [Docker image 저장 자동화 ](https://devocean.sk.com/blog/techBoardDetail.do?ID=163350)
- [Github Actions 트러블슈팅 ](https://white-world.tistory.com/411)
- [Ubuntu 머신에 Docker, Docker Compose 설치](https://iseunghan.tistory.com/408)
- [Docker 설치 후 권한 관련 오류](https://github.com/occidere/TIL/issues/116)
- [Fork한 저장소의 싱크](http://www.notforme.kr/archives/1631)
- [Docker + Github Action + Spring Boot 자동배포환경 만들기](https://velog.io/@rmswjdtn/Spring-Docker-Github-Action-Spring-Boot-자동배포환경-만들기)