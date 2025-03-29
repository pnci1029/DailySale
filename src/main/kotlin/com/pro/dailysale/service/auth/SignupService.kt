package com.pro.dailysale.service.auth

import com.pro.dailysale.config.JwtTokenProvider
import com.pro.dailysale.domain.user.User
import com.pro.dailysale.domain.user.UserRepository
import com.pro.dailysale.domain.user.enums.UserRole
import com.pro.dailysale.service.auth.dto.OauthUserInfo
import com.pro.dailysale.service.auth.dto.TokenResponseDTO
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