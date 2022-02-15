package com.kihyeonkim.springtest.user

import com.kihyeonkim.springtest.user.model.User
import com.kihyeonkim.springtest.user.service.UserService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.EmptyResultDataAccessException
import javax.persistence.EntityNotFoundException

@SpringBootTest
class UserServiceTest {

	@Autowired
	private lateinit var userService: UserService

	private fun makeDefaultUser() = User()

	@Test
	fun findInvalidUser() {
		val user = userService.getUserInfo(1000L)

		assert(user == makeDefaultUser())
	}

	@Test
	fun findValidUser() {
		val addedUserId = userService.addUser(makeDefaultUser())

		val userInfo = userService.getUserInfo(addedUserId)

		assert(userInfo.id != -1L)
	}

	@Test
	fun modifyInvalidUser() {
		assertThrows<EntityNotFoundException> { userService.modifyUserName(10000000L, "test") }
	}

	@Test
	fun modifyValidUser() {
		val renewalName = "test"

		val addedUserId = userService.addUser(makeDefaultUser())

		assertDoesNotThrow { userService.modifyUserName(1L, renewalName) }

		val userInfo = userService.getUserInfo(addedUserId)

		assert(userInfo.name == renewalName)
	}

	@Test
	fun testDeleteInvalidUser() {
		assertThrows<EmptyResultDataAccessException> { userService.deleteUserById(10000000000L) }
	}

	@Test
	fun testDeleteValidUser() {
		val addedUserId = userService.addUser(makeDefaultUser())

		assertDoesNotThrow { userService.deleteUserById(addedUserId) }
	}


	@Test
	fun testFindInvalidUser() {
		assert(!userService.isUserExist(100000L))
	}

	@Test
	fun testFindValidUser() {
		val addedUserId = userService.addUser(makeDefaultUser())

		assert(userService.isUserExist(addedUserId))
	}
}