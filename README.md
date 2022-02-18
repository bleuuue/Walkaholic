# Walkaholic

<img src="https://user-images.githubusercontent.com/79898245/154683354-430101e7-b5b9-4d25-bc21-2b057d4a00c9.png">

---

## **[ About ]**
> ### **즐겁게 산책하고 기록하는 서비스**

어떤 사용자든 재밌게 운동하고 산책할 수 있는 서비스를 제공하고자 합.

### Intention
1. 산책을 싫어하는 사람도 즐겁게 산책할 수 있다.

2. 운동 기록을 확인할 수 있어 성취감과 같은 긍정적인 에너지를 얻는다.

3. 유저 위치 기반 날씨와 주위 편의시설 등의 산책 환경을 알 수 있다.

### Identity
* 캐릭터 : 운동량에 따라 성장한다

* 챌린지 : 운동량에 따라 보상을 받을 수 있다

* 테마길 : 걷고싶은 산책길을 추천한다

### Development period
21.04.28 ~ 21.05.28

---

## **[ Technical Skills ]**

*   **Front-end Development**
    *   **Kotlin, Retrofit2, Coroutine, Observer**
*   Back-end Development
    *   Springboot, RDS+Spatial, AWS EC2, S3
*   Tools
    *   Android Studio, GitHub, HeidiSQL, Postman, Swagger, Figma, Jandi

## **[ Architecture ]**
> ### MVVM
**: Model, View, VidwModel**
- **Model** : 어플리케이션에서 사용되는 데이터와 그 데이터를 처리한다.
- **View** : 사용자에게 UI를 제공한다.
- **View Model** : View를 표현하기 위해 데이터 처리를 하는 View를 위한 Model이다. 

1. View에 사용자의 Action이 들어오면 View Model에 Action을 전달한다.<br>
2. View Model은 Model에게 데이터를 요청하고 응답받은 데이터를 가공하여 저장한다.<br>
3. View는 Videw Model과 Data Binding 하여 화면을 나타낸다.

**>> View와 Model 사이 의존성을 없애고 유지보수 및 테스트를 쉽게 하도록 하기 위해 MVVM을 채택하였다.**

---

## **[ Role ]**
### **Front-end Developer**
* 산책 기록 캘린더
* 테마별 산책 경로
* 산책 챌린지 (일일미션, 주간미션, 랭킹)

---

## **[ Feature Implementation ]**
<img src="https://user-images.githubusercontent.com/79898245/154691753-8338d1c2-34f7-4045-93e3-5289a968f934.png">

- 산책 기록 캘린더

      — 캘린더 제공 / 오늘 날짜 표시
      
      — 산책 기록 일자 표시
    
      — 선택한 일자 운동 기록 조회
      
      — 총 산책기록 / 세부산책기록 목록
      
- 테마별 산책 경로
      — 힐링 / 데이트 / 운동 테마 제공
    
      — 테마 타이틀, 위치, 거리, 소요시간 정보 제공
      
      — 선택 테마 세부 정보 페이지로 이동

- 산책 챌린지 (일일미션, 주간미션, 랭킹)
      — 일일/ 주간 미션 제공, 포인트 획득
    
      — 미션 진행 상황 및 달성 여부 표시
      
      — 누적 / 월별 포인트 랭킹 정보 제공

---
