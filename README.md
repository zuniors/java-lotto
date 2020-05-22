# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

--- 
# 1단계 - 문자열 덧셈 계산기
## 주어진 기능 요구사항
- 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환 
    - (예: “” => 0, "1,2" => 3, "1,2,3" => 6, “1,2:3” => 6)
- 앞의 기본 구분자(쉼표, 콜론)외에 커스텀 구분자를 지정할 수 있다. 
    - 커스텀 구분자는 문자열 앞부분의g “//”와 “\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다. 
    - 예를 들어 “//;\n1;2;3”과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
- 문자열 계산기에 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw한다.

## 기능 요구사항 목록 
#### StringCalculator
- [x] 빈 문자열 또는 null 값을 입력할 경우 0을 반환해야 한다.(예 : “” => 0, null => 0)
- [x] 음수를 전달할 경우 RuntimeException 예외가 발생해야 한다. (예 : “-1,2,3”)
- [x] 숫자 이외의 값을 전달할 경우 RuntimeException 예외가 발생해야 한다. (예: "a,1")
- [x] 숫자 하나를 문자열로 입력할 경우 해당 숫자를 반환한다.(예 : “1”)
- [x] 숫자 두개를 컴마(,) 구분자로 입력할 경우 두 숫자의 합을 반환한다.(예 : “1,2”)
- [x] 구분자를 컴마(,) 이외에 콜론(:)을 사용할 수 있다. (예 : “1,2:3” => 6)
- [x] “//”와 “\n” 문자 사이에 커스텀 구분자를 지정할 수 있다. (예 : “//;\n1;2;3” => 6)
    - ; : & @ # %   = - ! ] }
- [x] “//”와 “\n” 문자 사이에 정규표현식 meta char 를 커스텀 구분자로 지정할 수 있다.
    - . | ^ $ * + ? ( [ { )
- [x] 커스텀 구분자 형식의 문자열 중 숫자값을 빈 문자열로 입력한 경우 0을 반환한다.
    - (예 : “//:\n” => 0) (차이 : “//:\n2:3::” => 6)
    
#### Number
- [x] 음수값 문자의 경우 예외가 발생한다.
- [x] 숫자 이외의 값을 전달하는 경우 예외가 발생한다.
- [x] 빈 문자열 또는 null 값을 입력할 경우 예외가 발생한다.
- [x] 0 이상의 양의 값 문자가 들어오면 Number 객체를 생성할 수 있다.
- [x] 인수로 받은 값과 합한 값의 Number 객체를 반환한다.
- [x] 자신의 숫자값을 반환할 수 있다.

#### Splitter
- 공통
    - 빈 문자열을 입력하면 빈 String 배열을 반환한다.
    - null 을 입력하면 NullPointerException 예외 발생
    - [x] 문자열이 커스텀 구분자 형식이 아니면 기본 구분자로 split 한다.
    - [x] 문자열이 커스텀 구분자 형식이면 커스텀 구분자로 split 한다.
- 기본 구분자
    - [x] 빈 문자열을 입력하면 빈 String 배열을 반환한다.
    - [x] null 을 입력하면 NullPointerException 예외 발생
    - [x] 콜론(:) 또는 콤마(,)를 구분자로 분리한 String 배열을 반환한다.
    - [x] 기본 구분자는 기본으로 문자열 계산기에 지원되는 구분자이므로 항상 지원 가능하다.
- 커스텀 구분자 
    - [x] 빈 문자열을 입력하면 빈 String 배열을 반환한다.
    - [x] null 을 입력하면 NullPointerException 예외 발생
    - [x] 매칭되지 않는 정규 표현식 문자열 들어오면 예외 발생 
    - [x] 커스텀 구분자로 분리한 숫자 String 배열을 반환한다.
    - [x] 커스텀 구분자는 문자열이 정규표현식 패턴에 매칭이 되어야 지원 가능하다.
        - (private) 커스텀 구분자 형식의 문자열인지 확인할 수 있다.
- Splitter 관리자
    - [x] 우선 커스텀 구분자에 매칭되는 문자열이면 커스텀 구분자를 Splitter 로 반환한다.
    - [x] 커스텀 구분자가 지원되지 않는 문자열이면 기본 구분자를 Splitter 로 반환한다.

#### StringUtil
- [x] 문자열이 빈 문자열이거나 null 인 경우 return true
- [x] 문자가 Integer 로 변환할 수 없는 값인 경우 NumberFormatException 예외 발생 

#### Exception
- [x] RuntimeException 대신 명확한 exception 을 사용한다.
- [x] 구체적인 Error Message 를 지정한다.

--- 
# 2단계 - 로또(자동)
## 주어진 기능 요구사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.
- 로또 자동 생성은 Collections.shuffle() 메소드 활용한다.
- Collections.sort() 메소드를 활용해 정렬 가능하다.
- ArrayList의 contains() 메소드를 활용하면 어떤 값이 존재하는지 유무를 판단할 수 있다.

## 기능 요구사항 목록 
### # M (Model)
#### Price
: 금액과 관련된 클래스
- [x] 입력받은 금액이 티켓 1장 이상의 금액이면 Price 객체를 생성한다. 
- [x] 입력받은 금액이 음수이면 예외가 발생한다.
- [x] 입력받은 금액이 티켓 1장을 금액보다 적으면 예외가 발생한다.
- [x] 구입가능한 티켓의 개수를 반환한다.

#### LottoNumber
: 로또 숫자 범위 내의 숫자 
- [x] 1 ~ 45 까지의 정수 이외의 값이면 예외가 발생한다.
- [x] 로또 번호 범위 1 ~ 45 사이의 정수면 로또 숫자를 생성할 수 있다.
- [x] equals 재정의 테스트 

#### LottoNumbers
: 로또 숫자에 대한 일급 컬렉션 
- [x] List<Integer> 을 받아 LottoNumbers 을 생성할 수 있다.
- [x] 로또 슷자의 개수가 6개가 아니면 예외가 발생한다.
- [x] 로또 슷자가 0 ~ 45 사이의 숫자가 아니면 예외가 발생한다.
- [x] 당첨 번호와 일치하는 숫자의 개수를 구할 수 있다.
- [x] 특정 숫자가 티켓에 포함되어 있으면 true 를 반환한다.
- [x] 중복된 숫자를 가질 수 없다. 
    - Set 으로 구성함.
- [x] 자동으로 생성된 로또 번호를 반환한다.
- [ ] Set<LottoNumber>의 값을 List<Integer> 로 구할 수 있다. 

#### LottoTicket
: LottoNumbers 에 대한 Wrapping 클래스 
- [x] LottoNumbers 가 null 이면 예외를 반환한다.
- [x] 우승 티켓과 일치하는 상금을 반환한다.
- [ ] LottoNumbers 의 값을 List<Integer> 로 구할 수 있다. 

#### LottoTickets
: LottoTicket 에 대한 일급 컬렉션
- [x] LottoNumbers List 가 null 이면 예외를 반환한다.
- [x] 구입한 모든 티켓의 우승 티켓과 일치하는 상금의 개수를 반환한다. 

#### WinningLottoTicket
: 로또 당첨 번호 
- [x] String 배열로 된 입력받은 정보로 로또 당첨번호를 생성할 수 있다.
- LottoTicket 과 동일 
    - [x] 로또 슷자의 개수가 6개가 아니면 예외가 발생한다.
    - [x] 로또 슷자가 0 ~ 45 사이의 숫자가 아니면 예외가 발생한다.
    - [x] 특정 숫자가 티켓에 포함되어 있으면 true 를 반환한다.
    
#### Prize
: 일치하는 개수에 대한 상금액 (당첨 개수별 상)
- [x] 일치하는 개수에 맞는 Prize 객체를 반환한다.
- [x] 1 ~ 6 이내의 일치하는 개수인 경우 예외가 발생한다.
- [x] 1, 2 개가 맞은 경우는 하나도 맞지 않은 경우와 동일한 상금을 반환한다.

#### LottoPrizeResult
: 당첨 개수에 일치하는 티켓의 개수를 관리 
- [x] 결과값을 초기화하면 당첨 개수에 일치하는 티켓 개수는 모두 0으로 설정된다.
- [x] 당첨 개수에 일치하는 티켓 개수를 설정할 수 있다.
- [x] 해당하는 Prize 의 개수를 1씩 증가시킬 수 있다. 
- [x] 총 수익률을 반환한다. 
    - (최종 상금액 / 구매액)
    - (private) 최종 상금액을 반환한다. : 상금액 별 (상금액 * 당첨된 티켓 개수 = 당첨액)의 합 
- 테스트 코드 
    - [x] LottoPrizeResult 생성 
    - [x] 예상되는 수익률 비교 (calculateProfitRate)
    - [x] 예상되는 일치하는 티켓 개수 비교 (getMatchedTicketCount)

#### LottoSeller
: LottoTickets 를 금액만큼 구매하는 책임 
- [x] 로또 1장의 가격(1000원)보다 낮은 금액을 내면 예외가 발생한다.
- [x] 로또 구입 금액에 맞는 로또 티켓을 반환한다.
    - (티켓의 크기로 알 수 있음) 로또 구입 금액에 따라 구입 개수를 구할 수 있다.
    - (동일) 로또 구입 개수 만큼의 로또 복권을 생성할 수 있다.

#### LottoNumberGenerator
: 로또 숫자를 랜덤으로 자동 생성하는 책임 
- [x] 1 ~ 45 중 6개의 숫자를 랜덤으로 생성할 수 있다.
- [x] 랜덤으로 생성한 숫자는 1 ~ 45 사이의 값이다.
- [x] 랜덤으로 생성한 숫자에 중복된 값은 없다.

#### LottoGame (테스트 코드 어떻게?)
: 구매한 티켓과 로또 당첨 번호로 로또 결과 수행 
- [x] 구매한 티켓과 로또 당첨 번호에 맞는 결과를 반환한다.
- 테스트 코드
    - [x] LottoGame 생성
    - [x] 생성 -> play -> LottoPrizeResult 의 당첨 개수로 비교

#### LottoTicketDTO
: 화면 출력에 필요한 정보에 대한 DTO
- [x] LottoTickets 으로부터 List<LottoTicketDTO> 를 반환할 수 있다.

### # V (View)
#### InputView
- [x] 구입금액을 입력받을 수 있다.
- [x] 지난 주 당첨 번호를 입력받을 수 있다.

#### ResultView
- [x] 구매한 티켓의 개수를 출력할 수 있다. 
- [x] 구매한 티켓의 번호를 출력할 수 있다.
- [x] 당첨 개수에 일치하는 티켓 개수를 출력할 수 있다.
- [x] 총 수익률을 출력할 수 있다. 

### # C (Controller)
#### LottoApplication
- input: 구매 금액 입력
- business logic: 티켓 구매  
- output: 구매 개수 출력
- output: 구매한 티켓 정보 출력 
- input: 지난 주 당첨 번호 입력
- business logic: 당첨 번호에 맞게 통계를 내는 게임 수행 
- output: 통계 결과 출력 

