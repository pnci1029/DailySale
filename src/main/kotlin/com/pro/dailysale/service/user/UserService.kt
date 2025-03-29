package com.pro.dailysale.service.user

import com.pro.dailysale.domain.user.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
) {
}