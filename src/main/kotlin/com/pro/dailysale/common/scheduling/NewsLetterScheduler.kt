package com.pro.dailysale.common.scheduling

import com.pro.dailysale.newsletter.domain.NewsLetterRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Component
class NewsLetterScheduler(
    val newsLetterRepository: NewsLetterRepository
) {

//    @Scheduled(cron = "0 0 10 * * * ") // at 12:00
    @Transactional
    @Scheduled(cron = "*/10 * * * * *")
    fun sendMail() {
        val todayNewLetters = newsLetterRepository.findTodayNewsLetter(LocalDate.now().atStartOfDay())
    }
}