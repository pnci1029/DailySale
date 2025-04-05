package com.pro.dailysale.newsletter.domain

import com.pro.dailysale.common.PageResponseDTO
import com.pro.dailysale.newsletter.controller.dto.NewsLetterPutDTO
import com.pro.dailysale.newsletter.controller.dto.NewsLetterResponseDTO
import org.springframework.data.domain.Pageable

interface NewsLetterRepositoryCustom {
    fun searchNewsLetter(pageable: Pageable, query: String?): PageResponseDTO<NewsLetterResponseDTO>?

    fun updateNewsLetter(dto: NewsLetterPutDTO):Long

    fun remove(idx: Long): Long
}