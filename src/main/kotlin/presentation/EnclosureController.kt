package org.example.presentation

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.domain.model.Enclosure
import org.example.domain.repository.EnclosureRepository
import java.util.*

fun Route.enclosureRoutes(enclosureRepository: EnclosureRepository) {
    route("/enclosure") {

        get {
            val all = enclosureRepository.findAll()
            call.respond(all)
        }

        get("/{id}") {
            val id = UUID.fromString(call.parameters["id"])
            val enclosure = enclosureRepository.findById(id)

            if (enclosure != null) {
                call.respond(enclosure)
            } else {
                call.respondText("Вольер не найден!", status = HttpStatusCode.NotFound)
            }
        }

        post {
            val enclosure = call.receive<Enclosure>()
            enclosureRepository.save(enclosure)
            call.respondText("Вольер добавлен!", status = HttpStatusCode.Created)
        }

        delete("/{id}") {
            val id = UUID.fromString(call.parameters["id"])
            enclosureRepository.deleteById(id)
            call.respondText("Вольер удален!")
        }
    }
}