package sketchboard

interface DomainException {
    val displayMessage: String
    val internalMessage: String
    val code: ErrorCode
    val cause: Throwable?
}

abstract class BaseDomainException(
    override val code: ErrorCode,
    override val displayMessage: String,
    override val internalMessage: String = displayMessage,
    override val cause: Throwable? = null
) : Exception(internalMessage, cause), DomainException

enum class ErrorCode(
    val code: String
) {
    BAD_REQUEST("BAD_REQUEST"),
    NOT_FOUND("NOT_FOUND"),
    SLOT_NOT_AVAILABLE("SLOT_NOT_AVAILABLE")
}

class NotFoundException(message: String) :
    BaseDomainException(code = ErrorCode.NOT_FOUND, displayMessage = message)

class BadRequestException(message: String, cause: Throwable? = null) :
    BaseDomainException(code = ErrorCode.BAD_REQUEST, displayMessage = message, cause = cause)

class SlotNotAvailableException(message: String) :
    BaseDomainException(code = ErrorCode.SLOT_NOT_AVAILABLE, displayMessage = message)
