package com.pro.dailysale.admin.service

import com.pro.dailysale.newsletter.controller.dto.NewsLetterPostDTO
import com.pro.dailysale.newsletter.domain.NewsLetter
import com.pro.dailysale.newsletter.domain.NewsLetterRepository
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val newsLetterRepository: NewsLetterRepository
){
    fun createNewsLetter(
        dto: NewsLetterPostDTO
    ) = apply { newsLetterRepository.save(
        NewsLetter(
            title = dto.title,
            content = dto.content,
            isSent = "N",
            sentAt = dto.sentAt
        )
    ) }
}