package com.kihyeonkim.springtest.user.service

import com.kihyeonkim.springtest.user.model.User
import com.kihyeonkim.springtest.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class UserService(private val userRepository: UserRepository) {
	fun getUserInfo(id: Long): User {
		return userRepository.findById(id).orElseGet { User() }
	}

	fun addUser(user: User): Long {
		return userRepository.save(user).id
	}

	@Transactional
	fun modifyUserName(id: Long, name: String) {
		val user = userRepository.findById(id).orElseThrow { EntityNotFoundException() }

		user.name = name

		userRepository.save(user)
	}

	fun deleteUserById(id: Long) {
			userRepository.deleteById(id)
	}

	fun isUserExist(id: Long): Boolean {
		return userRepository.existsById(id)
	}
}