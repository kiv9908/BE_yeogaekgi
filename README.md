<div align="center">
<h2>[2024] 여객기(yeogaekgi) ✈️</h2>
<b>여</b>행가이드가 될 관광<b>객</b>들의 <b>기</b>록<br> 외국인 관광객 선불카드 서비스를 제공하며 결제내역을 기반 리뷰 작성이 가능합니다. 💸
</div>


![여객기-팜플렛-001](https://github.com/user-attachments/assets/f52c3cf7-c093-4e97-b7eb-61c2c4432f15)


## 목차

- [개발 목표](#개발-목표)
- [사용 기술](#사용-기술)
- [Advanced Feature](#advanced-feature)
- [개선 사항](#개선-사항)

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
  - Spring Boot
  - Java
  - JPA
  - MySQL
- FE
  - JavaScript
  - React
  - HTML
  - CSS

## Advanced Feature

- AI 번역(DeepL AI) 기능 제공

  - 출발언어 자동 감지

    - request

      ```javaScript
      const deepLApi = (text, target_lang) => {
      const data = {
          text: [text],
          target_lang: target_lang,
      };

      return axios

          .post(protocol + 'api/translate', data, {
              headers: {
                  Authorization: token,
                  'Content-Type': 'application/json',
              },
          })
          .then((res) => {
              console.log(res.data.translations[0].text);
              return res.data.translations[0].text;
          })
          .catch((error) => {
              console.error('API 호출 오류:', error);
              throw error;
          });
      };
      ```

    - response (DeepLController)

      ```java
      package com.v6.yeogaekgi.util.DeepL;

      import org.springframework.beans.factory.annotation.Value;
      import org.springframework.http.HttpEntity;
      import org.springframework.http.HttpHeaders;
      import org.springframework.http.MediaType;
      import org.springframework.http.ResponseEntity;
      import org.springframework.web.bind.annotation.*;
      import org.springframework.web.client.RestTemplate;

      import java.util.Collections;

      @RestController
      @RequestMapping("/api")
      @CrossOrigin(origins = {"*"})
      public class TranslationController {

          private final String deeplApiUrl = "https://api-free.deepl.com/v2/translate";

          @Value("${deepl.auth-key}")
          private String authKey;

          @PostMapping("/translate")
          public ResponseEntity<TranslationDTOResponse> translate(@RequestBody TranslationDTORequest request) {
              String targetLang = request.getTarget_lang();

              String apiUrl = deeplApiUrl + "?target_lang=" + targetLang;
              RestTemplate restTemplate = new RestTemplate();

              HttpHeaders headers = new HttpHeaders();
              headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
              headers.setContentType(MediaType.APPLICATION_JSON);
              headers.add("Authorization", "DeepL-Auth-Key " + authKey);

              TranslationDTOResponse response = restTemplate.postForObject(apiUrl, createHttpEntity(request, headers), TranslationDTOResponse.class);

              return ResponseEntity.ok(response);
          }

          private HttpEntity<TranslationDTORequest> createHttpEntity(TranslationDTORequest request, HttpHeaders headers) {
              return new HttpEntity<>(request, headers);
          }
      }
      ```

## 개선 사항

- BE
  - Spring Security Filter와 Controller의 명확한 역할 정의 필요
  - 기능별로 통일된 예외처리
  - 공통영역(util) 인터페이스화 필요
- FE
  - 컴포넌트 재사용성을 고려한 수정 필요
  - 공통영역과 기능을 분리한 파일구조로 수정 필요요

