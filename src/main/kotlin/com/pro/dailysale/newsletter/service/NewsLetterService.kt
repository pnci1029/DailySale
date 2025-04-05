package com.pro.dailysale.newsletter.service

import com.pro.dailysale.newsletter.controller.dto.NewsLetterPostDTO
import com.pro.dailysale.newsletter.domain.NewsLetter
import com.pro.dailysale.newsletter.domain.NewsLetterRepository
import com.pro.dailysale.util.SecurityUtil.Companion.getCurrentUser
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NewsLetterService(
    val newsLetterRepository: NewsLetterRepository
) {

    @Transactional
    fun createNewsLetter(
        dto: NewsLetterPostDTO
    ) = getCurrentUser()
        .let {
            NewsLetter(
                title = dto.title,
                content = dto.content,
                isSent = "N",
                sentAt = dto.sentAt
            )
        }
        .let { newsLetterRepository.save(it) }
        .also { ResponseEntity.ok() }

    @Transactional(readOnly = true)
    fun getNewsLetters(
        pageable: Pageable,
        query: String?,
    ) = getCurrentUser()
        .let { newsLetterRepository.searchNewsLetter(pageable, query) }
}