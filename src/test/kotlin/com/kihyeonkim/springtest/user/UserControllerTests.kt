package com.kihyeonkim.springtest.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.kihyeonkim.springtest.user.model.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {
	@Autowired
	private lateinit var mockMvc: MockMvc

	@Autowired
	private lateinit var objectMapper: ObjectMapper

	@Autowired
	private lateinit var ctx: WebApplicationContext

	@BeforeEach
	fun beforeTest() {
		//한글 깨짐 방지
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
			.addFilters<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true)) // 필터 추가
			.build()
	}

	@Test
	fun getInvalidUrl() {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/notValidUri"))
			.andExpect(MockMvcResultMatchers.status().is4xxClientError)
			.andDo { println(it.response.contentAsString) }
	}

	@Test
	fun testGetUser() {
		mockMvc
			.perform(MockMvcRequestBuilders.get("/user/info").param("userId", "1"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andDo { println(it.response.contentAsString) }
	}

	@Test
	fun checkUserExist() {
		mockMvc
			.perform(MockMvcRequestBuilders.put("/user/exist").param("userId", "1"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(MockMvcResultMatchers.content().string("false"))
			.andDo { println(it.response.contentAsString) }

	}

	@Test
	fun checkUserNotExist() {
		mockMvc
			.perform(MockMvcRequestBuilders.put("/user/exist").param("userId", "10000"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(MockMvcResultMatchers.content().string("false"))
			.andDo { println(it.response.contentAsString) }

	}

	@Test
	fun addUser() {
		val user = User("이름", 10L, "나는 주소 나는 주소")

		val jsonUser = objectMapper.writeValueAsString(user)

		mockMvc
			.perform(
				MockMvcRequestBuilders.put("/user/add").contentType(MediaType.APPLICATION_JSON).content(jsonUser)
			)
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andDo { println(it.response.contentAsString) }
	}

	@Test
	fun checkModifyUser() {
		val user = User("이름", 10L, "나는 주소 나는 주소")

		val jsonUser = objectMapper.writeValueAsString(user)

		val addedUserId = mockMvc
			.perform(
				MockMvcRequestBuilders.put("/user/add").contentType(MediaType.APPLICATION_JSON).content(jsonUser)
			)
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andReturn().response.contentAsString.toLong()

		val changeParams = LinkedMultiValueMap<String, String>()
		changeParams.add("userId", addedUserId.toString())
		changeParams.add("newUserName", "바뀌었지롱")

		mockMvc.perform(
			MockMvcRequestBuilders.post("/user/modify").contentType(MediaType.APPLICATION_JSON).params(changeParams)
		)
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(MockMvcResultMatchers.content().string("true"))

		mockMvc
			.perform(MockMvcRequestBuilders.get("/user/info").param("userId", addedUserId.toString()))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andDo { println(it.response.contentAsString) }
	}

	@Test
	fun deleteUser() {
		val user = User("나는 지워질 이름", 10L, "나는 지워질 주소")

		val jsonUser = objectMapper.writeValueAsString(user)

		val addedUserId = mockMvc
			.perform(
				MockMvcRequestBuilders.put("/user/add").contentType(MediaType.APPLICATION_JSON).content(jsonUser)
			)
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andReturn().response.contentAsString.toLong()

		mockMvc
			.perform(MockMvcRequestBuilders.get("/user/info").param("userId", addedUserId.toString()))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andDo { println(it.response.contentAsString) }

		mockMvc
			.perform(MockMvcRequestBuilders.delete("/user/delete").param("userId", addedUserId.toString()))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(MockMvcResultMatchers.content().string("true"))

		mockMvc
			.perform(MockMvcRequestBuilders.put("/user/exist").param("userId", addedUserId.toString()))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(MockMvcResultMatchers.content().string("false"))
			.andDo { println(it.response.contentAsString) }
	}
}