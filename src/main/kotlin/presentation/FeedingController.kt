package org.example.presentation

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.application.service.FeedingOrganizationService
import org.example.domain.model.FeedingSchedule
import org.example.domain.repository.FeedingScheduleRepository
import org.jetbrains.annotations.Async
import java.util.*

fun Route.feedingRoutes(
    feedingScheduleRepository: FeedingScheduleRepository,
    feedingService: FeedingOrganizationService
) {
    route("/feeding") {

        get("/schedules") {
            val all = feedingScheduleRepository.findAll()
            call.respond(all)
        }

        post("/schedules") {
            val schedule = call.receive<FeedingSchedule>()
            feedingScheduleRepository.save(schedule)
            call.respondText("Расписание кормления добавлено!", status = HttpStatusCode.Created)
        }

        post("/feed/{animalID}") {
            val animalID = UUID.fromString(call.parameters["animalId"])
            val event = feedingService.feedAnimal(animalID)
            call.respond(event)
        }
    }
}