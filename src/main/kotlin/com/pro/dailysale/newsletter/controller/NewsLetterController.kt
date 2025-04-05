package com.pro.dailysale.newsletter.controller

import com.pro.dailysale.newsletter.controller.dto.NewsLetterPostDTO
import com.pro.dailysale.newsletter.service.NewsLetterService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/news")
class NewsLetterController (
    val newsLetterService: NewsLetterService
){
    @PostMapping
    fun createNewsLetter(
        @RequestBody dto: NewsLetterPostDTO
    ) = newsLetterService.createNewsLetter(dto)

    @GetMapping
    fun getNewsLetters(
        @RequestParam(required = false) query: String?,
        @PageableDefault(
            sort = ["createAt"],
            direction = Sort.Direction.DESC,
            size = 7,
            page = 0
        ) pageable: Pageable,
    ) = newsLetterService.getNewsLetters(pageable, query)
}