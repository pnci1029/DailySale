package com.pro.dailysale.controller.auth

import com.pro.dailysale.service.auth.GoogleAuthService
import com.pro.dailysale.service.auth.SignupService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class SignupController(
    val signupService: SignupService,
    val googleAuthService: GoogleAuthService // 구글 인증 처리용 서비스
) {

    @GetMapping("/google")
    fun googleAuthUrl(
        response: HttpServletResponse,
    ): ResponseEntity<*> {
        val authUrl = googleAuthService.generateAuthorizationUrl(response)
        return ResponseEntity.ok(mapOf("url" to authUrl))
    }

    @GetMapping("/google/callback")
    fun googleCallback(@RequestParam code: String): ResponseEntity<*> {
        val tokenInfo = googleAuthService.getAccessToken(code)

        // 토큰을 이용해 사용자 정보 가져오기
        val userInfo = googleAuthService.getUserInfo(tokenInfo.accessToken)

        // 해당 이메일로 가입된 사용자가 있는지 확인 후 로그인 또는 회원가입 처리
        val user = signupService.findOrCreateUser(userInfo)

        // JWT 토큰 생성 및 반환
        val jwtToken = signupService.generateToken(user)

        return ResponseEntity.ok(mapOf("token" to jwtToken, "user" to user))
    }
}