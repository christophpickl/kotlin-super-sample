package kss.server.app

import assertk.assertThat
import io.ktor.http.HttpMethod
import io.ktor.server.testing.handleRequest
import kss.server.app.testInfrastructure.isJsonContentType
import kss.server.app.testInfrastructure.isStatusCodeOk
import kss.server.app.testInfrastructure.withKtor
import org.testng.annotations.Test

@Test
class ClassesKtorTest {
    fun `When get users path Then return OK and JSON content type`() = withKtor {
        handleRequest(HttpMethod.Get, "/classes").apply {
            assertThat(response).isStatusCodeOk()
            assertThat(response).isJsonContentType()
//            response.content
        }
    }
}
