package com.pro.dailysale.domain

import com.pro.dailysale.domain.enums.UserRole
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 100)
    val userEmail: String,

    @Column(nullable = false, length = 50)
    val userName: String,

    @Column(nullable = true, length = 1)
    val isActive: String = "Y",

    @Enumerated(EnumType.STRING)
    val role: UserRole,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime
){
}