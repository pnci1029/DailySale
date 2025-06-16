package com.pro.dailysale.subscribe.domain

interface SubscribeRepositoryCustom {
    fun findByUserEmail(email: String): List<Subscriber>
}