package com.pro.dailysale.service.auth.dto

data class TokenResponse(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String? = null,
    val token_type: String
)
