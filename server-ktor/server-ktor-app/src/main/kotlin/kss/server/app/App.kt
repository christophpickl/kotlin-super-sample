package kss.server.app

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kss.sharedlib.common.ClassRto
import kss.sharedlib.common.UserRto
import mu.KotlinLogging.logger

object App {
    private val log = logger {}
    @JvmStatic
    fun main(args: Array<String>) {
        log.info { "Starting up server ..." }
        embeddedServer(Netty, port = 9090) {
            kssModule()
        }.start(wait = true)
    }
}

fun Application.kssModule() {
    install(ContentNegotiation) {
        jackson { }
    }
    install(CallLogging)
    install(CORS) {
        host("localhost:8080") // allow local Kotlin/JS to access this via CORS headers
    }



    routing {
        route("/users") {
            get {
                call.respond(listOf(UserRto(
                    id = "dummyId",
                    name = "maxmuster"
                )))
            }
        }
        route("/classes") {
            get {
                call.respond(listOf(ClassRto(
                    id = "dummyId",
                    title = "dummy title"
                )))
            }
        }
    }
}
