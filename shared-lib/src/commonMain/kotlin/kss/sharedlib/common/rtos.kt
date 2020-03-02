package kss.sharedlib.common

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

@Serializable
data class UserRto(
    val id: String,
    val name: String
) {
    companion object {
        val listSerializer = UserRto.serializer().list
    }
}

fun Json.parseUserTos(result: String): List<UserRto> = parse(UserRto.listSerializer, result)

@Serializable
data class ClassRto(
    val id: String,
    val title: String
)
