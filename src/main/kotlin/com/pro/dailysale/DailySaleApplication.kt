package com.pro.dailysale

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class DailySaleApplication

fun main(args: Array<String>) {
    runApplication<DailySaleApplication>(*args)
}
