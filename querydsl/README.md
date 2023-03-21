# QueryDSL Practice

## Q클래스 생성

Gradle탭 -> ohter -> compileQuerydsl

## 기본문법

1. searchAll

    동적 쿼리를 처리하기 위한 메소드를 오버라이드 한 것

2. selectFrom(product)

    "product"가 저장되어 있는 테이블로 부터 조회

3. leftJoin(product.tags, tag1)

    product의 tag와 tag1을 leftJoin

4. fetchJoin()

   3번의 leftJoin을 패치 조인을 적용

5. where()

    여러가지의 조건들을 입력하는 것

6. offset(pageable.getOffset())
   
   데이터를 가져올 레코드의 시작점을 결정해주는 메소드

7. limit(pageable.getPageSize())
   
   가져올 레코드의 개수를 정함

8. orderBy()

    나온 값들을 정렳 해주는 메소드
9. fetch()

    query를 생성하고, 결과를 리스트로 반환하는 메소드

