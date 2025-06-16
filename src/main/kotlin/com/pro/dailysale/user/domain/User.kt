package com.pro.dailysale.user.domain

import com.pro.dailysale.util.CreateAndUpdateAudit
import com.pro.dailysale.user.domain.enums.UserRole
import jakarta.persistence.*

@Entity
@Table(name = "users")
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
) : CreateAndUpdateAudit()