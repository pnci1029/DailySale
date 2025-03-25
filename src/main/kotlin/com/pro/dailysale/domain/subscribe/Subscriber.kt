package com.pro.dailysale.domain.subscribe

import com.pro.dailysale.domain.CreateAndUpdateAudit
import jakarta.persistence.*

@Entity
data class Subscriber(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 100)
    val userEmail: String,

    @Column(nullable = true, length = 1)
    val isSubscribed: String = "Y",

) : CreateAndUpdateAudit()