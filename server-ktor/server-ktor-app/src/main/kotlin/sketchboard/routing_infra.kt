package sketchboard

import io.ktor.application.ApplicationCall
import java.util.UUID

fun ApplicationCall.parameterAsUuid(key: String): UUID {
    val paramValue = getParameter(key)
    return parseUuid(paramValue)
}

fun ApplicationCall.getParameter(key: String) =
    parameters[key] ?: throw BadRequestException("Please set parameter '$key'.")

fun parseUuid(maybeUuid: String): UUID =
    try {
        UUID.fromString(maybeUuid)
    } catch (e: IllegalArgumentException) {
        throw BadRequestException("Invalid UUID: '$maybeUuid'", e)
    }
