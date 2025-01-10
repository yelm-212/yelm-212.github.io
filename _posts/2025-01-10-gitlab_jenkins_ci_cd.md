---
title: "GitLab Jenkins CI&CD 구축"
excerpt: "GitLab, Jenkins, CI&CD 구축 아키텍쳐와 트러블슈팅 과정에 대해 작성하였습니다."
tags: 
  - [GitLab, Jenkins, CI, CD]
toc: true
toc_sticky: true
date: 2025-01-10
last_modified_at: 2025-01-10
---

![](/attatchments/Pasted%20image%2020250110175438.png)

다음과 같은 형태로 **GitLab Repository**와 **Jenkins**를 활용하여 CI/CD 파이프라인을 구성하였다.  

**GitLab**과 **Jenkins**는 리눅스 서버에 설치되어 운영되고 있다.

1. **CI (Continuous Integration)**:
    - 개발자가 GitLab Repository에 코드를 푸시하면, GitLab의 **Webhook**이 트리거된다.
    - Webhook은 Jenkins CI 파이프라인을 호출하여 Spring Boot 애플리케이션을 빌드하고, 이를 통해 `jar` 파일을 생성한다.
2. **CD (Continuous Deployment)**:
    - 생성된 `jar` 파일은 SSH를 통해 원격 Linux 서버로 전송된다.
    - 전송된 `jar` 파일이 원격 서버에서 실행되면서 Spring Boot 애플리케이션이 배포된다.

해당 파이프라인의 흐름을 Mermaid 다이어그램으로 확인할 수 있다.
 [GitLab+Jenkins 파이프라인 구축](https://rkdejr2321.github.io/ci-cd/gitlab_jenkins/) 글을 참고하였다.

---

## Troubleshooting

**Send build artifacts over SSH** 플러그인을 사용해 CD를 적용하는 과정에서 

```bash
ERROR: Exception when publishing, exception message [Exec exit status not zero. Status [127]]
Build step 'Send build artifacts over SSH' changed build result to UNSTABLE
```

위와 같은 오류가 발생하였다.

찾아보니, **Transfer Set > Exec command** 실행시
작성해둔 bash shell script 위치를 찾지 못해서 발생하는 문제로 보였다.

실제 리눅스 환경에서도 `127` 오류는 해당하는 명령을 찾지 못했을때 발생하는 오류라고 한다.
그리고 해당 플러그인으로 명령어 실행 시에  command line을 1줄만 제대로 읽어오는 것으로 보였다.
그래서 Exec Command의 실제 명령어를 한 줄로 줄이고, 다음과 같이 설정하니 성공했다.

```bash
cd {shell script file 경로} && bash {파일명}.sh
```

또, bash shell 파일의 실행 권한을 모든 유저가 실행 가능하게 설정해야 정상적으로 실행된다.

```bash
chmod 755 deploy.sh
```