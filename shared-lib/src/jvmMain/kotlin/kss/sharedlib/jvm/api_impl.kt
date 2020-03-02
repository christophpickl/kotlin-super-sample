package kss.sharedlib.jvm

import com.github.kittinunf.fuel.Fuel
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kss.sharedlib.common.ApiException
import kss.sharedlib.common.ServerApi
import kss.sharedlib.common.UserRto
import kss.sharedlib.common.parseUserTos

fun main() {
    println(JvmServerApi(baseUrl = "http://0.0.0.0:9090").fetchUsers())
}

/** Using Fuel and Kotlin.serializationx */
@UseExperimental(ImplicitReflectionSerializer::class)
class JvmServerApi(
    private val baseUrl: String
) : ServerApi {

    private val json = Json(
        configuration = JsonConfiguration.Stable
    )

    // TODO test this
    override fun fetchUsers(): List<UserRto> {
        val (_, response, result) =
            Fuel.get("$baseUrl/users").apply {
                executionOptions.responseValidator = { true }
            }.responseString()

        if (response.statusCode != 200) {
            throw ApiException("Server returned unexpected status code ${response.statusCode}! ($response)")
        }

        return json.parseUserTos(result.get())
    }

}
