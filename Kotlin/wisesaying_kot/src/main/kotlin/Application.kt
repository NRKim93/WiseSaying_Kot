package com.nrkimprogect.thegym

import com.nrkimprogect.thegym.controller.WiseSayingController
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {
    @Bean
    fun consoleRunner(ctx: ApplicationContext) = CommandLineRunner {
        // ApplicationContext에서 WiseSayingController 빈을 가져옴
        val controller = ctx.getBean(WiseSayingController::class.java)
        runConsoleApp(controller)
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

