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
```