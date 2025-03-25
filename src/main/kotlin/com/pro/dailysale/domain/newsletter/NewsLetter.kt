package com.pro.dailysale.domain.newsletter

import com.pro.dailysale.domain.CreateAndUpdateAudit
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