package kss.server.app

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.contentType
import io.ktor.server.testing.handleRequest
import org.testng.annotations.Test

@Test
class AppKtorTest {
    fun `When get root path Then return OK and JSON content type`() = withKtor {
        handleRequest(HttpMethod.Get, "").apply {
            assertThat(response.status()).isEqualTo(HttpStatusCode.OK)
            assertThat(response.contentType().withoutParameters()).isEqualTo(ContentType.Application.Json)
        }
    }
}
