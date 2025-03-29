package com.pro.dailysale.controller.user

import com.pro.dailysale.controller.user.dto.UserResponseDTO
import com.pro.dailysale.service.user.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(
    val userService: UserService,
) {
//    @GetMapping("/current-user")
//    fun getCurrentUser(): UserResponseDTO  = userService.getCurrentUser()
}