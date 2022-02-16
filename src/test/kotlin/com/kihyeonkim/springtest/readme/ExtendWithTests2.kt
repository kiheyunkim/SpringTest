package com.kihyeonkim.springtest.readme

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.*
import java.lang.reflect.Method
import java.util.logging.Logger


@ExtendWith(TimingExtension::class)
class ExtendWithTests2 {
	@Test
	fun injectsInteger() {
		assertNotEquals(1, 0)
	}

	@Test
	fun injectsDouble() {
		assertEquals(0.0, 1.0)
	}

}

class TimingExtension : BeforeTestExecutionCallback, AfterTestExecutionCallback {
	@Throws(Exception::class)
	override fun beforeTestExecution(context: ExtensionContext) {
		logger.info { "start" }
		getStore(context).put(START_TIME, System.currentTimeMillis())
	}

	@Throws(Exception::class)
	override fun afterTestExecution(context: ExtensionContext) {
		val testMethod: Method = context.requiredTestMethod
		val startTime: Long = getStore(context).remove(START_TIME, Long::class.javaPrimitiveType)
		val duration = System.currentTimeMillis() - startTime
		logger.info {
			String.format(
				"Method [%s] took %s ms.",
				testMethod.name,
				duration
			)
		}
	}

	private fun getStore(context: ExtensionContext): ExtensionContext.Store {
		return context.getStore(ExtensionContext.Namespace.create(javaClass, context.requiredTestMethod))
	}

	companion object {
		private val logger: Logger = Logger.getLogger(TimingExtension::class.java.name)
		private const val START_TIME = "start time"
	}
}