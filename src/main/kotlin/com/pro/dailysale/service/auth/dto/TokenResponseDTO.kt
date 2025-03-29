package com.pro.dailysale.service.auth.dto

data class TokenResponseDTO(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiresIn: Long
)
