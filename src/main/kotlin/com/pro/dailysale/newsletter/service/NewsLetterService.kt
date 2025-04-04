package com.pro.dailysale.newsletter.service

import com.pro.dailysale.newsletter.controller.dto.NewsLetterPostDTO
import com.pro.dailysale.newsletter.domain.NewsLetter
import com.pro.dailysale.newsletter.domain.NewsLetterRepository
import com.pro.dailysale.util.SecurityUtil.Companion.getCurrentUser
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class NewsLetterService(
    val newsLetterRepository: NewsLetterRepository
) {
    fun createNewsLetter(
        dto: NewsLetterPostDTO
    ) {

        val user = getCurrentUser()
        NewsLetter(
            title = dto.title,
            content = dto.content,
            isSent = "N",
            sentAt = dto.sentAt
        )
//            .also { newsLetterRepository.save(it) }
            .also { ResponseEntity.ok() }
    }
//    ) = NewsLetter(
//        title = dto.title,
//        content = dto.content,
//        isSent = "N",
//        sentAt = dto.sentAt)
//        .also { newsLetterRepository.save(it) }
//        .also { ResponseEntity.ok() }
}