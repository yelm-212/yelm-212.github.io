---
permalink: /portfolio/
layout: single
title: " "
toc: true
toc_sticky: true
toc_label: "Portfolio"
last_modified_at: 2023-08-10
---
# Portfolio

## Projects

### RECLOSET \| 2023-06-28 ~ 2023-07-27 (약 5주)

환경을 위한 중고 의류 판매 쇼핑몰을 제작하였습니다.

- [GitHub Repository](https://github.com/codestates-seb/seb44_main_017/tree/main)
- [GitHub Actions CI & CD가 적용된 GitHub Repository](https://github.com/yelm-212/seb44_main_017_test)
- [배포 링크](http://recloset-bucket.s3-website.ap-northeast-2.amazonaws.com/)

#### DB Schema

![image](/attatchments/recloset_ERD.png)

#### My Roles 

- BE 팀원
- DB Schema

##### 사용한 기술 스택

- Java, Spring Boot Framework, JPA, QueryDSL
- DB : MySQL
- Deploy : AWS(EC2, RDS), Docker
- Git : Git, GitHub


##### 기능 구현 및 역할 

1. Product의 기본 CRUD
2. Product의 좋아요, 조회수 기능
	- 하나의 Product에 한 명의 유저가 조회수를 중복하여 반영하지 않도록 구현
	- 하나의 Product에 하나의 유저가 좋아요를 중복하여 반영하지 않도록 구현 / 기본 CRUD
	- 동시성 처리 오류가 발생하지 않도록 구현
3. Product Comment CRUD
4. [GitHub Actions + Docker + AWS EC2 CI & CD](https://yelm-212.github.io/docker_k8s/docker-ci-cd/)
	- [Docker Hub 이미지](https://hub.docker.com/repository/docker/21yrshin/seb44_main_017/general) 생성
	- CI & CD 적용을 위해 GitHub Actions 사용
		- Docker-compose
		- EC2 자동 배포


##### 향후 보완점 및 업데이트 계획

1. Product Controller, Service Layer 리팩토링
2. 단위 테스트 코드 작성하기
3. 레거시 코드 제거

---

### RISC-V simulator \| 2022-12-01 ~ 2022-12-20 (약 3주) \| 개인 프로젝트 (Python)

RISC-V instruction의 변환된 binary file을 실행시켜주는 simulator를 python으로 작성하였습니다.

- [GitHub Repository](https://github.com/yelm-212/RISC-V-simulator/blob/main/README.md)

#### 실행 방법 및 실행 결과

우선, RISC-V assembly instruction 파일을 binary로 변환한다.
이후 이 Python3 프로그램과 binary file을 실행하게 되면, 간단한 RISC-V 구조의 시뮬레이션 결과가 출력된다.

```shell
python3 ./riscv_sim.py ./your_binary_file
```

![](/attatchments/riscv.png)


---
