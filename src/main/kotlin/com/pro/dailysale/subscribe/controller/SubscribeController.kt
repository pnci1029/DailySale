package com.pro.dailysale.subscribe.controller

import com.pro.dailysale.subscribe.controller.dto.SubscribePostDTO
import com.pro.dailysale.subscribe.service.SubscribeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/subscribe")
class SubscribeController (
    val subscribeService: SubscribeService
){

    @GetMapping("/{email}")
    fun validateSubscriber(
        @PathVariable("email") email: String
    ) = subscribeService.isEmailNotPresent(email)

    @PostMapping
    fun addSubscriber(
        @RequestBody dto: SubscribePostDTO
    ) = subscribeService.addSubscriber(dto)
}