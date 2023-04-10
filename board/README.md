# 기본 게시판 구현

## 환경

```
    Java 11
    SpringBoot 2.7.10
    PostgreSQL
```
    
## 기록

<details>
<summary>23.04.08</summary>

- [x] 프로젝트 생성 
- [x] DB 연동 
- [x] Member Entity 생성 

</details>

<details>
<summary>23.04.09</summary>

- [x] DTO 에서 toEntity 처리
- [x] MemberController POST Method 틀 생성
- [x] MemberController POST, GET, DELETE 구현


<ul>
<li>SpringBoot 프로젝트 흐름 이해 필요</li>
<li>Lombok 어노테이션 공부</li>
<li>response 방식 찾아보기 >> ResponseEntity 사용</li>
<li>Java Collection, Generic 공부</li>
<li>Entity 수정할 때 접근 방법, 수정 방식 찾아보기(update 메소드?)</li>
</ul>
</details>

<details>
<summary>23.04.10</summary>

- [x] BoardController POST, GET, PUT, DELETE 구현


<ul>
<li>Member id를 가지고 조회하는 GET이 작동하지 않음 쿼리는 제대로 나감 컬럼 이름 혹은 FK 접근에서 문제???</li>
<li>Spring Security 찾아보기</li>
<li>DTO > Entity or Entity > DTO 의 과정은 어디에서 진행 되어야 하는가? 장단점 찾아보기</li>
</ul>
</details>