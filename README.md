# JAVA-SWING-Room-Cafe-KIOSK-
22-05-22 / ver 1.0 
자바 스윙 GUI 프로그래밍으로 키오스크 시스템을 구현한 프로젝트입니다.

1. 프로젝트 설계

![프로젝트 개요](https://user-images.githubusercontent.com/84646738/169689642-eb243ec1-75b8-4fdd-9641-210fabe30f4e.jpg)

![프로젝트 구성1](https://user-images.githubusercontent.com/84646738/169689643-7bed21ea-ddf9-46f6-961d-ea6ea2ccbdd1.jpg)

![프로젝트 구성2](https://user-images.githubusercontent.com/84646738/169689644-ff598a7d-bfac-40de-8cb6-3bde52db9792.jpg)

![json 데이터 파싱](https://user-images.githubusercontent.com/84646738/169689623-02d1da64-35e0-4b29-ada0-ef6966dda3ad.jpg)

![데이터 구성](https://user-images.githubusercontent.com/84646738/169689627-7892bb9e-279b-40be-bad5-c0d9f4473f8a.jpg)

데이터를 계정별로 관리하기 위해 로컬 환경에 JSON파일을 생성해 파싱하고 수정하는 방법을 사용했습니다.
파싱한 데이터를 자주 사용하는 경우, static 변수로 받아 파싱을 여러번 하는 것을 방지했습니다.


2. 프로젝트 구성

![로그인](https://user-images.githubusercontent.com/84646738/169689628-427fb016-cf76-48e6-8285-159b97fb2a49.jpg)

JSON 데이터를 활용하여 로그인과 회원가입 기능을 구현했습니다. 입력한 정보를 JSON내에 존재하는 데이터와 일치하는지 확인하는 방법으로 로그인 기능을 수행합니다.

![회원가입](https://user-images.githubusercontent.com/84646738/169689645-b8e79e68-73b3-4abe-b915-070713b4705a.jpg)

새로운 계정을 생성합니다. 특정 값을 입력하지 않았을 경우, 아이디 중복 확인을 하지 않았을 경우, 비밀번호 재확인이 일치하지 않을 경우 등 예외처리를 해두었습니다.

![보유이용권선택](https://user-images.githubusercontent.com/84646738/169689631-97344575-c87e-4bea-97f5-44217edfc184.jpg)

로그인 한 계정이 보유한 이용권을 보여주는 화면입니다.
이용권은 시간권, 기간권으로 분류했습니다.
시간권은 분단위로 계산되며, 기간권은 일단위로 계산됩니다.
예를들어 시간권으로 룸을 대실한 후 30분이 지나면 잔여 시간권의 값이 30분만큼 차감되는 반면,
기간권을 사용할 경우, 24시간을 이용할 수 있지만 1분만 사용하고 퇴실을 했더라도 하루 단위로 차감이 됩니다.

![이용권 구매하기1](https://user-images.githubusercontent.com/84646738/169689634-6cfe0b25-5366-409b-a749-891df3ac4ae5.jpg)

![이용권 구매하기2](https://user-images.githubusercontent.com/84646738/169689636-431e3171-78f7-477b-892f-ee3b23c12ba4.jpg)

![파일저장3](https://user-images.githubusercontent.com/84646738/169689641-64168381-0ed2-4197-9aab-948082e535ce.jpg)

이용권 구매하기를 선택하면 시간권과 기간권을 구매할 수 있는 화면으로 이동합니다.
여기서 영수증을 출력하는 기능을 선택하면 C:\KIOSK\KIOSK_RECEIPT 경로에 구매한 이용권에 대한 정보를 담은 파일을 생성합니다.
파일 이름 중복을 방지하기 위해 '회원이름+이용권 구매 시간' 으로 파일 이름을 지정했습니다.

![룸선택](https://user-images.githubusercontent.com/84646738/169689629-7b7f9627-a15f-4949-8381-dfd076f3c6f8.jpg)

이용권을 사용하면 룸선택 화면으로 이동합니다.
빨간색 버튼은 이미 다른 계정으로 대실 처리가 되어있는 룸이라서 파란색으로 되어있는 룸만을 선택할 수 있습니다.
해당 계정이 특정 룸을 대실했다면, 해당 계정은 퇴실버튼과 음식주문 버튼이 활성화됩니다.
룸 버튼은 비활성화 처리가 되기 때문에 다른 룸을 선택하려면 먼저 퇴실을 해야합니다.
룸을 대실한 상태에서 프로그램을 종료하고 다른 회원 정보로 로그인을 했을 경우 해당 룸이 이미 대실 처리가 되어있으므로 빨간색 버튼으로 보이게 됩니다.
JSON 파일 내에서 중요한 데이터를 관리하고 있기 때문에 프로그램을 종료해도 특정 기록이 사라지지 않습니다.

![음식 주문](https://user-images.githubusercontent.com/84646738/169689632-fe77618d-6db0-4de5-9445-faae7f9b7bf4.jpg)

![파일저장2](https://user-images.githubusercontent.com/84646738/169689640-cad6f9c2-9445-4a88-861b-47fb3cbd3319.jpg)

룸을 대실한 상태에서는 음식 주문 기능을 사용할 수 있습니다.
상단에 있는 음식 종류 탭을 선택하면 데이터에 있는 음식을 종류별로 화면에 나타냅니다.
음식 이미지를 클릭하면 아래 주문 내역 테이블에 데이터가 추가됩니다.
원하는 음식의 셀을 클릭하고 +, -버튼을 클릭하면 음식의 수량을 조절할 수 있고, X를 누르면 수량에 상관없이 선택된 행의 데이터를 모두 제거합니다.
음식이 추가되거나 제거될때마다 화면 오른쪽 하단에서 선택한 음식들의 가격 총합을 나타내도록 했습니다.
이용권 구매와 마찬가지로, 사용자가 요금을 적게 입력하지 않았는지, 숫자가 아니라 문자열을 입력하진 않았는지 등에 대한 예외처리를 설정했습니다.
영수증 출력을 선택한다면 C:\KIOSK\KIOSK_FOOD_RECEIPT 경로에 파일이 저장됩니다.


![퇴실하기](https://user-images.githubusercontent.com/84646738/169689638-8c85079d-a5df-4a71-9a8d-bfad0da425c8.jpg)

![퇴실완료](https://user-images.githubusercontent.com/84646738/169689637-44119676-d4c3-4c12-a81f-909991f8247c.jpg)

방을 얼마나 사용했는지에 대해 계산하고 해당 룸을 대실 가능 상태로 전환합니다. 이제 다른 계정에서도 해당 룸을 대실할 수 있을 것입니다.
방을 사용했던 시간을 계산하여 잔여 시간권 또는 기간권에서 차감합니다.
이제 해당 사용자는 이용권을 사용하거나 구매할 수 있게 되었습니다.

![관리자모드](https://user-images.githubusercontent.com/84646738/169689624-26485f39-45f1-4d62-8aaa-57907a0d5e71.jpg)

![image](https://user-images.githubusercontent.com/84646738/169691315-eb8871ce-40d1-4e3e-bdaa-493f84a92838.png)

id : admin0320   password : admin0320
위의 정보로 로그인하면 관리자 모드로 접근할 수 있습니다. 지금은 JSON데이터 초기화 기능만을 구현했습니다.
소스 코드로 구현한 디폴트 값으로 JSON 데이터를 초기화합니다.
향후 여러가지 기능들을 추가할 예정입니다.



3. 소스 파일 테스트 전 참고사항
![파일저장1](https://user-images.githubusercontent.com/84646738/169689639-079e3eab-4121-4741-99a9-c2095d4539c1.jpg)
중요 데이터를 위의 경로에서 보관하고 파싱하도록 하고 있습니다. 경로를 생성하지 않아도 자동으로 만들게 해두었지만, 경로 에러가 나는 경우가 종종 있기 때문에 같은 이름으로
위와 같은 경로에 폴더를 생성하는 것을 권장합니다.

![파일배치](https://user-images.githubusercontent.com/84646738/169690949-7139a1aa-a0bf-4c94-82ff-6d0948d9c784.jpg)
이클립스에서 열람하는 것을 권장합니다. 인텔리제이에서 지원하지 않는 메서드를 사용했기 때문에 빌드가 되지 않을 수 있습니다.
자바 파일과 images내의 파일들을 위의 이미지와 같은 형태로 배치하세요.


JSON 파일을 생성하고 파싱하는데에 json-simple-1.1.1.jar 외부 라이브러리를 사용했습니다. 아래의 링크에서 파일을 다운받으세요.
https://code.google.com/archive/p/json-simple/downloads

![image](https://user-images.githubusercontent.com/84646738/169691031-a7e55d9d-93ab-4f2c-a2dd-f2f03d1b3dc5.png)

프로젝트에 외부라이브러리를 추가하는 방법입니다. 프로젝트 이름에 오른쪽 마우스 클릭을 해서 Configure Build Path를 선택합니다.

![image](https://user-images.githubusercontent.com/84646738/169691108-7675f59e-7125-4fa7-a626-8761cbee000a.png)
classpath 누르고 add external jar 누르고 위에서 다운받은 파일 선택한다음 apply and close 누르면 라이브러리를 적용할 수 있습니다.

메인 파일은 logIn.java 입니다.
