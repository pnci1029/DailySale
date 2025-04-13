package com.pro.dailysale.subscribe.controller

import com.pro.dailysale.subscribe.controller.dto.SubscribePostDTO
import com.pro.dailysale.subscribe.service.SubscribeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/subscribe")
class SubscribeController (
    val subscribeService: SubscribeService
){

    @PostMapping
    fun addSubscriber(
        @RequestBody dto: SubscribePostDTO
    ) = subscribeService.addSubscriber(dto)
}