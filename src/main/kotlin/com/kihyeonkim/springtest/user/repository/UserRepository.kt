package com.kihyeonkim.springtest.user.repository

import com.kihyeonkim.springtest.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>