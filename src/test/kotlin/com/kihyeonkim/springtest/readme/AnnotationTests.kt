package com.kihyeonkim.springtest.readme

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.TimeUnit

@BeforeAll
fun beforeAll() {
	println("beforeAll")
}

@AfterAll
fun afterAll() {
	println("afterAll")
}

@SpringBootTest
class AnnotationTests {

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
	fun testTimeOut(){
		Thread.sleep(5000)
		println("timeout")
	}

	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	@Test
	fun testTimeOut2(){
		Thread.sleep(10000)
		println("timeout")
	}

	@EnabledOnOs(OS.MAC)
	@Test
	fun cannotExecuteOnWindows(){
		println("mac")
	}


}