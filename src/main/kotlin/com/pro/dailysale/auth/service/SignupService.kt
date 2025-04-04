package com.pro.dailysale.auth.service

import com.pro.dailysale.config.JwtTokenProvider
import com.pro.dailysale.user.domain.User
import com.pro.dailysale.user.domain.UserRepository
import com.pro.dailysale.user.domain.enums.UserRole
import com.pro.dailysale.auth.service.dto.OauthUserInfo
import com.pro.dailysale.auth.service.dto.TokenResponseDTO
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.net.URLEncoder

@Service
class SignupService (
    val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    @Value("\${target.origins}") private val origin: String,
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
    fun generateToken(user: User): TokenResponseDTO {
        return jwtTokenProvider.createTokens(user)
    }

    fun loginSuccess(jwtToken: TokenResponseDTO, response: HttpServletResponse) {
        val params = mapOf(
            "accessToken" to jwtToken.accessToken,
            "refreshToken" to jwtToken.refreshToken
        )

        val queryString = params.entries.joinToString("&") { (key, value) ->
            "$key=${URLEncoder.encode(value, "UTF-8")}"
        }
        val authUrl = "$origin/success?$queryString"
        response.sendRedirect(authUrl)
    }
}