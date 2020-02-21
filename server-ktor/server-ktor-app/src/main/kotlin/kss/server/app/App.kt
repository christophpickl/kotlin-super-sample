package kss.server.app

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kss.server.api.ClassRto
import mu.KotlinLogging.logger

object App {
    private val log = logger {}
    @JvmStatic
    fun main(args: Array<String>) {
        log.info { "Starting up server ..." }
        embeddedServer(Netty, port = 8080) {
            kssModule()
        }.start(wait = true)
    }
}

fun Application.kssModule() {
    install(ContentNegotiation) {
        jackson { }
    }
    routing {
        get {
            call.respond(ClassRto(
                id = "dummyId",
                title = "dummy title"
            ))
        }
    }
}
