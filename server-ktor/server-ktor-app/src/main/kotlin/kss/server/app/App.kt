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
import kss.sharedlib.common.HomeRto
import kss.sharedlib.common.UserRto
import mu.KotlinLogging.logger
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance

data class Konfig(
    val port: Int,
    val baseUrl: String
)

object App {
    private val log = logger {}
    @JvmStatic
    fun main(args: Array<String>) {
        val konfig = Konfig(
            port = 9090,
            baseUrl = "http://localhost:9090"
        )
        log.info { "Starting up server ..." }
        embeddedServer(Netty, port = konfig.port) {
            kssModule(applicationKodein(konfig))
        }.start(wait = true)
    }
}

fun applicationKodein(konfig: Konfig) = Kodein {
    bind<Konfig>() with instance(konfig)
}

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
