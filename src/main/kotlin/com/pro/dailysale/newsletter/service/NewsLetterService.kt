package com.pro.dailysale.newsletter.service

import com.pro.dailysale.newsletter.controller.dto.NewsLetterDeleteDTO
import com.pro.dailysale.newsletter.controller.dto.NewsLetterPostDTO
import com.pro.dailysale.newsletter.controller.dto.NewsLetterPutDTO
import com.pro.dailysale.newsletter.controller.dto.NewsLetterResponseDTO
import com.pro.dailysale.newsletter.domain.NewsLetter
import com.pro.dailysale.newsletter.domain.NewsLetterRepository
import com.pro.dailysale.util.SecurityUtil.Companion.getCurrentUser
import com.pro.dailysale.util.exception.CustomException
import com.pro.dailysale.util.exception.ErrorCode
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

    @Transactional
    fun updateNewsLetter(
        dto: NewsLetterPutDTO
    ) = getCurrentUser()
        .let { newsLetterRepository.updateNewsLetter(dto) }
        .let {
            if (it >= 1) {
                ResponseEntity.ok().body(it)
            } else {
                throw CustomException(ErrorCode.CODE_404)
            }
        }

    @Transactional
    fun removeNewsLetters(dto: NewsLetterDeleteDTO): ResponseEntity<Map<String, Any>> {
        var deletedCount = 0L

        dto.idxList.forEach { idx ->
            val result = newsLetterRepository.remove(idx)


            deletedCount += result
        }

        return ResponseEntity.ok().body(mapOf(
            "success" to true,
            "deletedCount" to deletedCount,
            "message" to "뉴스레터 삭제가 완료되었습니다."
        ))
    }
}