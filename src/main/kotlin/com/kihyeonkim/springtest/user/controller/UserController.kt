package com.kihyeonkim.springtest.user.controller

import com.kihyeonkim.springtest.user.model.User
import com.kihyeonkim.springtest.user.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

	@GetMapping("/info")
	fun getUserInfo(@RequestParam(name = "userId") userId: Long): User {
		return userService.getUserInfo(userId)
	}

	@PutMapping("/add")
	fun putUserInfo(@RequestBody user: User): Long {
		return userService.addUser(user)
	}

	@PostMapping("/modify")
	fun postModifyUserInfo(userId: Long, newUserName: String): Boolean {
		userService.modifyUserName(userId, newUserName)

		return true;
	}

	@DeleteMapping("/delete")
	fun deleteUserInfo(userId: Long): Boolean {
		userService.deleteUserById(userId)

		return true
	}

	@PutMapping("/exist")
	fun checkUserExist(userId: Long): Boolean {
		return userService.isUserExist(userId)
	}
}