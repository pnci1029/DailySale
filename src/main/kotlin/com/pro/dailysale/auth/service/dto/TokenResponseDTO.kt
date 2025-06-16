package com.pro.dailysale.auth.service.dto

data class TokenResponseDTO(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiresIn: Long
)
