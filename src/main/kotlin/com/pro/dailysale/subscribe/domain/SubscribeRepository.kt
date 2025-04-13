package com.pro.dailysale.subscribe.domain

import org.springframework.data.jpa.repository.JpaRepository

interface SubscribeRepository: JpaRepository<Subscriber, Long> {
}