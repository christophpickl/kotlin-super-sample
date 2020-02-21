package kss.server.app

import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.withTestApplication

fun withKtor(testCode: TestApplicationEngine.() -> Unit) {
    withTestApplication({
        kssModule()
    }, testCode)
}
