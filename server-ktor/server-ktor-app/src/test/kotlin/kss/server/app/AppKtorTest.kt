package kss.server.app

import assertk.assertThat
import io.ktor.http.HttpMethod
import io.ktor.server.testing.handleRequest
import kss.server.app.testInfrastructure.isContentJsonEquals
import kss.server.app.testInfrastructure.isJsonContentType
import kss.server.app.testInfrastructure.isStatusCodeOk
import kss.server.app.testInfrastructure.withKtor
import org.testng.annotations.Test

@Test
class AppKtorTest {
    fun `When get root path Then return OK and JSON content type`() = withKtor {
        handleRequest(HttpMethod.Get, "/").apply {
            assertThat(response).isStatusCodeOk()
            assertThat(response).isJsonContentType()
            assertThat(response).isContentJsonEquals("""{
                    "usersLink" : "http://localhost:9090/users",
                    "classesLink" : "http://localhost:9090/classes"
                }""")
        }
    }
}
