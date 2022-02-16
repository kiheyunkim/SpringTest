package com.kihyeonkim.springtest.readme

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.*
import java.lang.reflect.Parameter


@ExtendWith(RandomParametersExtension::class)
class ExtendWithTests {
	@Test
	fun injectsInteger(@Random i: Int, @Random j: Int) {
		assertNotEquals(i, j)
	}

	@Test
	fun injectsDouble(@Random d: Double) {
		assertEquals(0.0, d, 1.0)
	}

}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Random

class RandomParametersExtension : ParameterResolver {


	override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
		return parameterContext.isAnnotated(Random::class.java)
	}

	override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
		return getRandomValue(parameterContext.parameter, extensionContext)
	}

	private fun getRandomValue(parameter: Parameter, extensionContext: ExtensionContext): Any {
		val type: Class<*> = parameter.type
		val random = extensionContext.root.getStore(ExtensionContext.Namespace.GLOBAL)
			.getOrComputeIfAbsent(java.util.Random::class.java)
		if (Int::class.javaPrimitiveType == type) {
			return random.nextInt()
		}
		if (Double::class.javaPrimitiveType == type) {
			return random.nextDouble()
		}
		throw ParameterResolutionException("No random generator implemented for $type")
	}
}