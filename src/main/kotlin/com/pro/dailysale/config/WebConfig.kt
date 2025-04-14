package com.pro.dailysale.config

import com.pro.dailysale.util.security.ApiKeyInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    @Value("\${admin.newsletter.key}") private val apiSecret: String
) : WebMvcConfigurer {

    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(ApiKeyInterceptor(apiSecret))
            .addPathPatterns("/api/admin/**") // 관리자 API 경로에만 적용
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000", "http://119.205.220.134")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
    }
}