package com.pro.dailysale.newsletter.domain

import com.pro.dailysale.util.CreateAndUpdateAudit
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class NewsLetter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val title: String,
    val content: String,

    @Column(nullable = true, length = 1)
    val isSent: String = "N",

    @Column(nullable = false)
    val sentAt: LocalDateTime,

) : CreateAndUpdateAudit()