package com.pro.dailysale.service.auth.dto

data class TokenInfo(
    val accessToken: String,
    val refreshToken: String?,
    val expiresIn: Int
)
