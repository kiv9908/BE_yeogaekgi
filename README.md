<div align="center">
<h2>[2024] 여객기(yeogaekgi) ✈️</h2>
<b>여</b>행가이드가 될 관광<b>객</b>들의 <b>기</b>록<br> 외국인 관광객 선불카드 서비스를 제공하며 결제내역을 기반 리뷰 작성이 가능합니다. 💸<br>
[🔗](https://github.com/v6-yeogaekgi/FE_yeogaekgi) 여객기 frontend repo | [🔗](https://github.com/v6-yeogaekgi/BE_yeogaekgi) 여객기 backend repo
</div><br>


![여객기-팜플렛-001](https://github.com/user-attachments/assets/f52c3cf7-c093-4e97-b7eb-61c2c4432f15)

## 목차


- [개발 목표](#개발-목표)
- [사용 기술](#사용-기술)
- [Advanced Feature](#advanced-feature)
- [개선 사항](#개선-사항)
- [Architecture](#Architecture)

## 팀원 구성

<div align="center">

|                   **황세현**                    |                   **김제인**                    |              **백승준**               |               **서석환**                |              **이봉욱**               |               **정현진**                |
| :---------------------------------------------: | :---------------------------------------------: | :-----------------------------------: | :-------------------------------------: | :-----------------------------------: | :-------------------------------------: |
| [HwangSettong](https://github.com/HwangSettong) | [jane-kim-279](https://github.com/jane-kim-279) | [mint723](https://github.com/mint723) | [shseo232](https://github.com/shseo232) | [kiv9908](https://github.com/kiv9908) | [nijnuyhj](https://github.com/nijnuyhj) |

</div>

<br>

## 개발 목표

- BE
  - REST API 개발
  - Spring Security와 JWT 적용한 로그인 구현
  - JPA 이해 및 사용
  - Github Action 이용한 CI/CD 구현
  - 외부 API 활용
- FE
  - 재사용성이 높은 React 컴포넌트 설계
  - 무한 스크롤 구현

## 사용 기술

- BE

  - Spring Boot, Java, JPA, MySQL

- FE

  - JavaScript, React, HTML, CSS

- 버전 및 이슈관리

  - Git
  - Github

- 협업 툴

  - Discord, Notion, Google Calender, Google Sheet

- 서비스 배포 환경

  - AWS EC2, Docker, Nginx

- 디자인
  - Figma

## Architecture

![CI_CD - System Architecture](https://github.com/user-attachments/assets/e1ffe1df-3bfe-4447-acd7-739bb4180d1e)


## 개선 사항

- BE
  - Spring Security Filter와 Controller의 명확한 역할 정의 필요
  - 기능별로 통일된 예외처리
  - 공통영역(util) 인터페이스화 필요
- FE
  - 컴포넌트 재사용성을 고려한 수정 필요
  - 공통영역과 기능을 분리한 파일구조로 수정 필요

