package com.pro.dailysale.user.controller.dto

import com.pro.dailysale.user.domain.enums.UserRole

data class UserResponseDTO(
    val id: Long,
    val userName: String,
    val userEmail: String,
    val role: UserRole
)
