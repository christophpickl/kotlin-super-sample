package kss.server.app.testInfrastructure

import assertk.Assert
import assertk.assertions.isEqualTo
import assertk.assertions.prop
import assertk.assertions.support.expected
import assertk.assertions.support.show
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.TestApplicationResponse
import io.ktor.server.testing.contentType
import io.ktor.server.testing.withTestApplication
import kss.server.app.Konfig
import kss.server.app.applicationKodein
import kss.server.app.kssModule

fun withKtor(testCode: TestApplicationEngine.() -> Unit) {
    withTestApplication({
        kssModule(applicationKodein(Konfig(
            port = 0,
            baseUrl = "http://anyBaseUrl"
        )))
    }, testCode)
}

fun Assert<TestApplicationResponse>.isStatusCodeOk() {
    prop("status", TestApplicationResponse::status).isEqualTo(HttpStatusCode.OK)
}

fun Assert<TestApplicationResponse>.isContentJsonEquals(expectedJson: String, mode: JsonMode = JsonMode.Strict) {
    given { response ->
        val content = response.content ?: throw IllegalStateException("Response content must not be null!")
        assertThat(content).isJsonEquals(expectedJson, mode)
    }
}

// shortcut for: assertThat(response.contentType().withoutParameters()).isEqualTo(ContentType.Application.Json)
fun Assert<TestApplicationResponse>.isJsonContentType() {
    given { response ->
        val contentType = response.contentType()
        // ignoring any additional parameters
        if (contentType.match(ContentType.Application.Json)) return
        expected("to have content type ${ContentType.Application.Json} but was: ${show(contentType)}")
    }
}
