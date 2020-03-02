package sketchboard

import java.time.LocalDate

data class InvitationRequestRto(
    val ranges: Collection<DateRangeRto>
)

data class InvitatedRto(
    val invitationId: String
    // even better: val invitationUrl: String
)

data class InvitationRto(
    val id: String,
    val slots: Collection<SlotRto>
)

data class SlotRto(
    val id: String,
    val range: DateRangeRto,
    val state: SlotStateRto
)

data class ReservedSlotRto(
    val slot: SlotRto,
    val form: ReservationFormRto
)

enum class SlotStateRto(
    val discriminator: String
) {
    Available("available"),
    Reserved("reserved")
}

data class DateRangeRto(
    val start: LocalDate,
    val end: LocalDate
)

data class ReserveRequestDto(
    val slotId: String,
    val form: ReservationFormRto
)

data class ReservationFormRto(
    val name: String,
    val email: String
)

// ApiErrorRto ( message, error_code, exception? (message, stacktrace) )
