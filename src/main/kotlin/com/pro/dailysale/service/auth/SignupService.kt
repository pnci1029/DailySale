package com.pro.dailysale.service.auth

import com.pro.dailysale.domain.user.User
import com.pro.dailysale.domain.user.UserRepository
import com.pro.dailysale.domain.user.enums.UserRole
import com.pro.dailysale.service.auth.dto.OauthUserInfo
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Date

@Service
class SignupService (
    val userRepository: UserRepository,
    @Value("\${jwt.ACCESS_TOKEN_EXPIRED}") private val accessTokenValidTime: Int,
    @Value("\${jwt.REFRESH_TOKEN_EXPIRED}") private val refreshTokenValidTime: Int,
    @Value("\${jwt.secret}") private val secretKey: String,
){

    @Transactional
    fun findOrCreateUser(userInfo: OauthUserInfo): User {
        val existingUser = userRepository.findByUserEmail(userInfo.email)

        // 기존 사용자가 있으면 반환
        if (existingUser != null) {
            return existingUser
        }

        // 새 사용자 생성
        val newUser = User(
            userEmail = userInfo.email,
            userName = userInfo.name,
            isActive = "Y",
            role = UserRole.USER
        )

        // 저장 후 반환
        return userRepository.save(newUser)
    }

    @Transactional
    fun generateToken(user: User): TokenResponseDto {
        val now = Date()
        val accessTokenExpiration = Date(now.time + accessTokenValidTime)
        val refreshTokenExpiration = Date(now.time + refreshTokenValidTime)

        // Access Token 생성
        val accessToken = Jwts.builder()
            .setSubject(user.userEmail)
            .claim("id", user.id)
            .claim("role", user.role.name)
            .setIssuedAt(now)
            .setExpiration(accessTokenExpiration)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()

        // Refresh Token 생성
        val refreshToken = Jwts.builder()
            .setSubject(user.userEmail)
            .setIssuedAt(now)
            .setExpiration(refreshTokenExpiration)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()

        // Refresh Token 저장 (DB 또는 Redis)
        saveRefreshToken(user.userEmail, refreshToken)

        return TokenResponseDto(
            accessToken = accessToken,
            refreshToken = refreshToken,
            accessTokenExpiresIn = accessTokenExpiration.time
        )
    }

    // Refresh Token을 저장하는 메서드
    private fun saveRefreshToken(userEmail: String, refreshToken: String) {
//        val user = userRepository.findByUserEmail(userEmail)
//        user?.let {
//            it.refreshToken = refreshToken
//            userRepository.save(it)
//        }
    }

    // TokenResponseDto 클래스 - 응답을 위한 DTO
    data class TokenResponseDto(
        val accessToken: String,
        val refreshToken: String,
        val accessTokenExpiresIn: Long
    )
}