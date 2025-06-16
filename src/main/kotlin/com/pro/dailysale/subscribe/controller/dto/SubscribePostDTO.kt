package com.pro.dailysale.subscribe.controller.dto

import com.pro.dailysale.subscribe.domain.NewsLetterFrequency

data class SubscribePostDTO(
    val userEmail: String,
    val frequency: NewsLetterFrequency,
    val isMarketingAgreed: Boolean
)