package com.kihyeonkim.springtest.readme

import org.assertj.core.util.Arrays
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestReporter
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.condition.DisabledIf
import org.junit.jupiter.api.condition.EnabledIf
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.aggregator.AggregateWith
import org.junit.jupiter.params.aggregator.ArgumentsAccessor
import org.junit.jupiter.params.aggregator.ArgumentsAggregator
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.NullSource
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import java.util.stream.Stream

@BeforeAll
fun beforeAll() {
	println("beforeAll")
}

@AfterAll
fun afterAll() {
	println("afterAll")
}

@SpringBootTest
//@DisplayName("여기도 바뀔껄?")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
class AnnotationTests {

	enum class Enums {
		ENUM1, ENUM2, ENUM3, ENUM4
	}

	@BeforeEach
	fun beforeEach() {
		println("beforeEach")
	}

	@AfterEach
	fun afterEach() {
		println("afterEach")
	}

	@Tag("test1_태그")
	@Test
	fun test1() {
		println("test1")
	}

	@Tag("test2_태그")
	@DisplayName("test2()대신에 이 이름이 나옴")
	@Test
	fun test2() {
		println("test2")
	}

	@Disabled
	@Test
	fun testDisabled() {
		println("this test Disabled")
	}

	@Timeout(value = 10, unit = TimeUnit.SECONDS)
	@Test
	fun testTimeOut() {
		Thread.sleep(5000)
		println("timeout")
	}

	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	@Test
	fun testTimeOut2_2() {
		Thread.sleep(10000)
		println("timeout")
	}

	@EnabledOnOs(OS.MAC)
	@Test
	fun cannotExecuteOnWindows() {
		println("mac")
	}

	fun thisFunctionReturnBoolean(): Boolean = true

	@Test
	@EnabledIf("thisFunctionReturnBoolean")
	fun enabledIfTest() {
		println("enabledIfTest")
	}

	@Test
	@DisabledIf("com.kihyeonkim.springtest.readme.ExternalClass#thisFunctionAlsoReturnBoolean")
	fun disabledIfTest() {
		println("disabledIfTest")
	}

	@Nested
	@DisplayName("InternalClass")
	inner class InternalClass {
		@BeforeEach
		fun internalClassBeforeEach() {
			println("InternalClass BeforeEach")
		}

		@Test
		fun test1() {
			println("InternalClass test1")
		}

		@Test
		fun test2() {
			println("InternalClass test2")
		}
	}

	@RepeatedTest(10)
	fun repeatedTest() {
		println("repeatedTest")
	}

	@RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
	fun repeatedTest2() {
		println("repeatedTest2")
	}

	@ParameterizedTest
	@ValueSource(longs = [1L, 2L, 3L])
	fun parameterizedTest1(value: Long) {
		println(value * 2)
	}

	@ParameterizedTest
	@ValueSource(strings = ["Kim", "Lee", "Park"])
	fun parameterizedTest2(value: String) {
		println("last name: $value")
	}

	@ParameterizedTest
	@NullSource
	fun parameterizedTest3(value: String?) {
		println("last name: $value")
	}

	@ParameterizedTest
	@EmptySource
	fun parameterizedTest4(value: String) {
		println("last name: $value")
	}

	@ParameterizedTest
	@NullAndEmptySource
	fun parameterizedTest5(value: String?) {
		println("last name: $value")
	}

	@ParameterizedTest
	@EnumSource(Enums::class)
	fun parameterizedTest6(enums: Enums) {
		println("enum: $enums")
	}

	@ParameterizedTest
	@EnumSource
	fun parameterizedTest7(enums: Enums) {
		println("enum: $enums")
	}

	@ParameterizedTest
	@EnumSource(names = ["ENUM1", "ENUM3"])
	fun parameterizedTest8(enums: Enums) {
		println("enum: $enums")
	}

	@ParameterizedTest
	@EnumSource(names = ["ENUM1", "ENUM3"], mode = EnumSource.Mode.EXCLUDE)
	fun parameterizedTest9(enums: Enums) {
		println("enum: $enums")
	}

	@ParameterizedTest
	@MethodSource("methodSourceMethod")
	fun parameterizedTest10(str: String) {
		println("str: $str")
	}

	@ParameterizedTest
	@MethodSource("methodSourceMethodStream")
	fun parameterizedTest11(str: String) {
		println("str: $str")
	}

	@ParameterizedTest
	@MethodSource
	fun parameterizedTest12(str: String) {
		println("str: $str")
	}

	companion object {
		@JvmStatic
		fun methodSourceMethod() = arrayOf("test1", "test2")

		@JvmStatic
		fun methodSourceMethodStream(): Stream<String> = Stream.of("test1", "test2")

		@JvmStatic
		fun parameterizedTest12(): Stream<String> = Stream.of("value1", "value2")
	}

	data class Person(val name: String, val address: String, val date: LocalDate)

	@ParameterizedTest
	@CsvSource(
		value =
		["thisIsName, 서울시, 2022-01-10", "NameIsName, 경기도, 2022-02-01"]
	)
	fun parameterizedTest12(@AggregateWith(PersonAggregator::class) person: Person) {
		println("person: $person")
	}

	class PersonAggregator : ArgumentsAggregator {
		override fun aggregateArguments(accessor: ArgumentsAccessor?, context: ParameterContext?): Any {
			return AnnotationTests.Person(
				accessor!!.getString(0),
				accessor.getString(1),
				accessor.get(2, LocalDate::class.java)
			)
		}
	}

	@ParameterizedTest(name = "{index}th test {0} ==========> {1}")
	@CsvSource(value = ["apple, red", "banana, yellow", "peach, pink"])
	fun parameterizedTest13(fruit: String, color: String) {

	}

	@ParameterizedTest
	@ValueSource(strings = ["apple", "banana", "pink"])
	fun parameterizedTest14(fruit: String, testReporter: TestReporter) {
		testReporter.publishEntry("fruits", fruit)
	}
}


object ExternalClass {
	@JvmStatic
	fun thisFunctionAlsoReturnBoolean(): Boolean = false
}