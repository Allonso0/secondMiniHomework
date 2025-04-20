package org.example.presentation

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.application.service.ZooStatisticsService

fun Route.statisticsRoutes(
    zooStatisticsService: ZooStatisticsService
) {
    route("/statistics") {
        get {
            val stats = zooStatisticsService.getStats()
            call.respond(stats)
        }
    }
}