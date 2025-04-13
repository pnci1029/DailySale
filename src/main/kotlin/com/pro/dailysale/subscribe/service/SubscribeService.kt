package com.pro.dailysale.subscribe.service

import com.pro.dailysale.common.ResponseDTO
import com.pro.dailysale.subscribe.controller.dto.SubscribePostDTO
import com.pro.dailysale.subscribe.controller.dto.SubscriberResponseDTO
import com.pro.dailysale.subscribe.domain.SubscribeRepository
import com.pro.dailysale.subscribe.domain.Subscriber
import com.pro.dailysale.util.comfortutil.ComfortUtil
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SubscribeService(
    val subscribeRepository: SubscribeRepository,
    private val comfortUtil: ComfortUtil
) {
    @Transactional
    fun addSubscriber(dto: SubscribePostDTO): ResponseEntity<SubscriberResponseDTO> =
        dto.userEmail
            .also { comfortUtil.validateEmail(it) }
            .let {
                Subscriber(
                    isSubscribed = "Y",
                    userEmail = it
                )
            }
            .let { subscribeRepository.save(it) }
            .let { SubscriberResponseDTO.of(it) }
            .let { ResponseEntity.ok(it) }
}