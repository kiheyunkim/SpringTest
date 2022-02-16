#Spring Test

#### 어노테이션 종류

```kotlin
//괄호 안은 JUnit4에서의 어노테이션
@BeforeEach //각각의 테스트 메소드가 실행되기 전에 실행 되어야 하는 메소드를 명시함.(@Before)

@AfterEach  //각각의 테스트 메소드가 실행된 후에 실행 되어야 하는 메소드를 명시함.(@After)

@BeforeAll //테스트 시작 전에 딱 한번만 실행 됨 //static이어야함

@AfterAll // 테스트가 끝난 후 딱 한번만 실행 됨 //static이어야함

@Nested //test 클래스안에 Nested 테스트 클래스를 작성할때 사용되며 static이 아닌 Inner class여아함. #

@Tag //테스트를 필터링 할때 사용함. 클래스, 매소드 레벨에 사용

@Disabled //테스트 클래스나 메소드를 비활성화(@Ignore)

@Timeout //주어진 시간 안에 테스트가 끝나지 않으면 실패함

@ExtendWith //extension을 등록한다. 이 어노테이션은 상속됨.(@RunWith)

@RegisterExtension // 필드를 통해 extension을 등록. private이 아니라면 상속됨

@TempDir //필드 주입이나 파라미터 주입을 통해 임시적 디렉토리를 제공할 때 사용

@EnabledOnOs //OS에 따라서 테스트를 진행함

@DisplayNameGeneration(조건) //주어진 조건에 따라서 메소드들 이름을 수정함
//조건 
// * Standard: 메소드 이름과 뒤에 붙는 괄호를 모두 보여줌
// * Simple: 메소드 이름만 보여줌
// * ReplaceUnderscores: 어더스코어를 빈칸으로 변형
// * IndicativeSentences: 테스트 클래스 이름과 테스트 메소드 이름 + 괄호를 보여줌

@Order //테스트 실행 순서를 지정

@TestMethodOrder(조건) //주어진 조건에 따라서 실행 순서를 지정
//조건 
// * DisplayName: displayName기반으로 정렬
// * MethodName: 메소드 이름으로 정렬
// * OrderAnnotation: @Order 어노테이션에 명시된 순서로 정렬
// * Random: 랜덤으로 정렬

@EnableIf
@DisableIf
//메소드를 지정해서 그 메소드의 리턴값이 true/false인 경우 에만 실행, 외부 메소드의 경우는 다 지정해줘야함

@RepeatedTest //주어진 횟수 만큼 테스트를 반복함 //name 기정을 통해 메소드 이름을 지정할 수 있음

@ParameterizedTest  //@Test와는 다르게 파라미터가 있는 테스트용
//아래는 파라미터 주입 관련
@ValueSource(strings = ["Kim", "Lee", "Park"])  //@ParameterizedTest에 필요한 파라미터 값을 전달함
@NullSource //@ParameterizedTest 메소드에 null을 제공함
@EmptySource //@ParameterizedTest 메소드에 빈배열, 빈값을 제공함
@NullAndEmptySource // @NullSource, @EmptySource를 합친 것
@EnumSource //Enum을 편리하게 사용할 수 있도록 해줌(이름 생략 가능 - 파라미터로 추정)
@MethodSource //메소드로 부터 값을 가져와 주입함 Stream, Array 다됨(이름 생략 가능 - source Method, test Method 이름 같은 경우)



```