package com.pro.dailysale.auth.service.dto

data class TokenInfo(
    val accessToken: String,
    val refreshToken: String?,
    val expiresIn: Int
)
