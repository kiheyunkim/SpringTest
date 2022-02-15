package com.kihyeonkim.springtest.user

import com.kihyeonkim.springtest.user.model.User
import com.kihyeonkim.springtest.user.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserRepositoryTest {
	@Autowired
	private lateinit var userRepository: UserRepository

	private fun makeTestUser() = User()

	@Test
	fun addUser() {
		val user = userRepository.save(makeTestUser())

		assert(user.id != -1L)
	}

	@Test
	fun findInvalidUser() {
		val user = userRepository.findById(100L)

		assert(!user.isPresent)
	}

	@Test
	fun findValidUser() {
		val user = userRepository.save(makeTestUser())

		val findUser = userRepository.findById(user.id)

		assert(findUser.isPresent)
	}

	@Test
	fun checkNotExistUser() {
		assert(!userRepository.existsById(100000L))
	}

	@Test
	fun checkExistUser() {
		val user = userRepository.save(makeTestUser())

		assert(userRepository.existsById(user.id))
	}

	@Test
	fun deleteUser() {
		val user = userRepository.save(makeTestUser())

		assert(userRepository.existsById(user.id))

		userRepository.delete(user)

		assert(!userRepository.existsById(user.id))
	}
}