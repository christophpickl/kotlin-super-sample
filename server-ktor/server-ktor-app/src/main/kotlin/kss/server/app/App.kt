package kss.server.app

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import mu.KotlinLogging.logger

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

data class Konfig(
    val port: Int,
    val baseUrl: String
)
