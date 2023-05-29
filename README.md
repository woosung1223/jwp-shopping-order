# jwp-shopping-order


<a href = "https://www.notion.so/step2-da784bf6f78b4ce8baa89d489ceb227e"> API 명세 (노션) </a>

# 기능 목록
---

## 기능 목록(API)
- [ ] 상품 관련 API
  - [ ] 상품 정보는 다음 두 정보까지 포함하도록 개선한다.
    - 포인트 적립비율
    - 포인트 적립 가능여부

- [ ] 유저 관련 API
  - [ ] 보유한 적립금을 조회할 수 있다.

- [ ] 주문 관련 API
  - [ ] 장바구니에 담은 상품들을 주문할 수 있다.
  - [ ] 사용자별로 주문 목록을 확인할 수 있다.
  - [ ] 사용자별로 특정 주문의 상세 정보를 확인할 수 있다.

## 기능 목록(Domain)
- [x] 상품 정보를 수정한다.
  - [x] 상품 정보는 포인트 적립비율, 포인트 적립 가능여부를 포함한다.

- [ ] 유저는 적립금을 가질 수 있다.
  - [ ] 유저에 대한 적립금 조회 요청 시, 보유한 적립금을 반환한다. 

- [ ] 장바구니에 담은 상품을 주문할 수 있다.
  - [ ] 상품 ID 목록을 통해 주문을 발행한다.
  - [ ] 사용한 포인트가 있다면, 사용자 포인트에서 차감한다. 

- [ ] 사용자 별로 주문 목록을 확인할 수 있다.

- [ ] 사용자 별로 특정 주문의 상세 정보를 확인할 수 있다.
