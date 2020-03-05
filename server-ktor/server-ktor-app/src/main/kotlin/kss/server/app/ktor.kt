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
import kss.sharedlib.common.ClassRto
import kss.sharedlib.common.HomeRto
import kss.sharedlib.common.UserRto
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

fun Application.kssModule(kodein: Kodein) {
    install(ContentNegotiation) {
        jackson { }
    }
    install(CallLogging)
    install(CORS) {
        anyHost() // allow local Kotlin/JS to access this via CORS headers
    }

    val konfig by kodein.instance<Konfig>()
    routing {
        route("/") {
            get {
                call.respond(HomeRto(
                    usersLink = "${konfig.baseUrl}/users",
                    classesLink = "${konfig.baseUrl}/classes"
                ))
            }
        }
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
