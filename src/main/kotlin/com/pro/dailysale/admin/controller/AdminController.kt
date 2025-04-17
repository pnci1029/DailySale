package com.pro.dailysale.admin.controller

import com.pro.dailysale.admin.service.AdminService
import com.pro.dailysale.newsletter.controller.dto.NewsLetterPostDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController @RequestMapping("/api/admin")
class AdminController (
    val adminService: AdminService
){

    @PostMapping("/news-letter")
    fun createNewsLetter(
        @RequestBody dto: NewsLetterPostDTO
    ) = adminService.createNewsLetter(dto)
}