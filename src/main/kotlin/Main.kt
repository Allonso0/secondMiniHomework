package org.example

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.example.application.service.AnimalTransferService
import org.example.application.service.FeedingOrganizationService
import org.example.application.service.ZooStatisticsService
import org.example.infrastructure.repository.IMAnimalRepository
import org.example.infrastructure.repository.IMEnclosureRepository
import org.example.infrastructure.repository.IMFeedingScheduleRepository
import org.example.presentation.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }

        swaggerModule()

        val animalRepo = IMAnimalRepository()
        val enclosureRepo = IMEnclosureRepository()
        val feedingScheduleRepo = IMFeedingScheduleRepository()

        val transferService = AnimalTransferService(animalRepo, enclosureRepo)
        val feedingService = FeedingOrganizationService(animalRepo, feedingScheduleRepo)
        val zooStatisticsService = ZooStatisticsService(animalRepo, enclosureRepo, feedingScheduleRepo)

        routing {
            animalRoutes(animalRepo, transferService)
            enclosureRoutes(enclosureRepo)
            feedingRoutes(feedingScheduleRepo, feedingService)
            statisticsRoutes(zooStatisticsService)
        }

    }.start(wait = true)
}