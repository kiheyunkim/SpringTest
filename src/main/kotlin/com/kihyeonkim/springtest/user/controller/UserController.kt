package com.kihyeonkim.springtest.user.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/user")
class UserController {

	@GetMapping("/")
	@ResponseBody
	fun getUserRoot(): String {
		return "root"
	}
}