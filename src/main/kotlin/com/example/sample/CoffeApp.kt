package com.example.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoffeApp

fun main(args: Array<String>) {
    runApplication<CoffeApp>(*args)
}
