package org.example.presentation

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.application.service.AnimalTransferService
import org.example.domain.model.Animal
import org.example.domain.repository.AnimalRepository
import java.util.*

fun Route.animalRoutes(
    animalRepository: AnimalRepository,
    transferService: AnimalTransferService
) {
    route("/animals") {

        get {
            val animals = animalRepository.findAll()
            call.respond(animals)
        }

        get("/{id}") {
            val id = UUID.fromString(call.parameters["id"])
            val animal = animalRepository.findById(id)

            if (animal != null) {
                call.respond(animal)
            } else {
                call.respondText("Животное не найдено!", status = HttpStatusCode.NotFound)
            }
        }

        post {
            val animal = call.receive<Animal>()
            animalRepository.save(animal)
            call.respondText("Животное добавлено!", status = HttpStatusCode.Created)
        }

        delete("/{id}") {
            val id = UUID.fromString(call.parameters["id"])
            animalRepository.deleteById(id)
            call.respondText("Животное удалено!")
        }

        post("/{id}/move") {
            val animalId = call.parameters["id"]
                ?: return@post call.respondText("ID животного не указан", status = HttpStatusCode.BadRequest)

            val endEnclosureId = call.request.queryParameters["endEnclosureId"]
                ?: return@post call.respondText("endEnclosureId не указан", status = HttpStatusCode.BadRequest)

            try {
                val animalUUID = UUID.fromString(animalId)
                val enclosureUUID = UUID.fromString(endEnclosureId)
                val event = transferService.transfer(animalUUID, enclosureUUID)
                call.respond(event)
            } catch (e: IllegalArgumentException) {
                call.respondText("Некорректный формат UUID", status = HttpStatusCode.BadRequest)
            }
        }
    }
}