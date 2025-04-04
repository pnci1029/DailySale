package com.pro.dailysale.newsletter.controller

import com.pro.dailysale.newsletter.controller.dto.NewsLetterPostDTO
import com.pro.dailysale.newsletter.service.NewsLetterService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/news")
class NewsLetterController (
    val newsLetterService: NewsLetterService
){
    @PostMapping
    fun createNewsLetter(
        @RequestBody dto: NewsLetterPostDTO
    ) = newsLetterService.createNewsLetter(dto)
}