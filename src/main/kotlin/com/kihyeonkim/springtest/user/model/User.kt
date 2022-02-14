package com.kihyeonkim.springtest.user.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
	var name: String = "default",
	var age: Long = -1,
	var address: String = "default",
	@Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0
)
