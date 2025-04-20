package org.example.presentation

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.swaggerModule() {
    routing {
        staticResources("/swagger-ui", "swagger-ui") {
            default("index.html")
        }

        get("/swagger.json") {
            call.respondText(
                this::class.java.classLoader.getResource("swagger/swagger.json")!!.readText(),
                contentType = ContentType.Application.Json
            )
        }

        get("/swagger") {
            call.respondRedirect("/swagger-ui/index.html?url=/swagger.json", permanent = true)
        }
    }
}