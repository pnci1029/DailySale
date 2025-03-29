package com.pro.dailysale.controller.user.dto

import com.pro.dailysale.domain.user.enums.UserRole

data class UserResponseDTO(
    val id: Long,
    val userName: String,
    val userEmail: String,
    val role: UserRole
)
