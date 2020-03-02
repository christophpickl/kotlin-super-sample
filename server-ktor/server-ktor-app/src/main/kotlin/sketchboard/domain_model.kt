package sketchboard

import java.time.LocalDate
import java.util.UUID

data class Invitation(
    val id: InvitationId,
    val slots: Slots
) {
    companion object {
        fun newByRequest(request: InvitationRequest) = Invitation(
            id = InvitationId(),
            slots = Slots(request.ranges.map {
                AvailableSlot(
                    id = SlotId(),
                    range = it
                )
            })
        )
    }

    fun replace(newSlot: ReservedSlot) =
        copy(slots = slots.replace(newSlot))
}

data class Slots(private val slots: Collection<Slot>) : Collection<Slot> by slots {
    fun replace(newSlot: ReservedSlot): Slots {
        require(slots.any { it.id == newSlot.id && it is AvailableSlot })

        val oldSlot = slots.associateBy { it.id }[newSlot.id]
            ?: throw NotFoundException("Slot not found: ${newSlot.id}")

        return Slots(slots.toMutableList().apply {
            remove(oldSlot)
            add(newSlot)
        })
    }
}

interface Slot {
    val id: SlotId
    val range: DateRange

    fun <T> onSubType(callback: SlotCallback<T>): T
}

interface SlotCallback<T> {
    fun onAvailable(slot: AvailableSlot): T
    fun onReserved(slot: ReservedSlot): T
}

data class AvailableSlot(
    override val id: SlotId,
    override val range: DateRange
) : Slot {
    override fun <T> onSubType(callback: SlotCallback<T>) = callback.onAvailable(this)

    fun reserve(form: ReservationForm) = ReservedSlot(
        id = id,
        range = range,
        form = form
    )
}

data class ReservedSlot(
    override val id: SlotId,
    override val range: DateRange,
    val form: ReservationForm
) : Slot {
    override fun <T> onSubType(callback: SlotCallback<T>) = callback.onReserved(this)
}

data class InvitationRequest(
    val ranges: Collection<DateRange>
)

data class ReserveRequest(
    val slotId: SlotId,
    val form: ReservationForm
)

data class ReservationForm(
    val name: String,
    val email: Email
)

data class Email(
    val address: String
)

data class DateRange(
    val start: LocalDate,
    val end: LocalDate
) {
    init {
        require(start.isBefore(end))
    }
}

data class InvitationId(
    val value: UUID
) {
    constructor() : this(UUID.randomUUID())

    override fun toString() = value.toString()
}

data class SlotId(
    val value: UUID
) {
    constructor() : this(UUID.randomUUID())

    override fun toString() = value.toString()
}
