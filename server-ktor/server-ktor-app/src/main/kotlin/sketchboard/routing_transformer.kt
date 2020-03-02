package sketchboard

fun Invitation.toInvitedRto() = InvitatedRto(
    invitationId = id.toString()
)

fun Invitation.toInvitationRto() = InvitationRto(
    id = id.toString(),
    slots = slots.map { it.toRto() }
)

fun Slot.toRto() = SlotRto(
    id = id.toString(),
    range = range.toRto(),
    state = onSubType(SlotStateRtoCallback)
)

private object SlotStateRtoCallback : SlotCallback<SlotStateRto> {
    override fun onAvailable(slot: AvailableSlot) = SlotStateRto.Available
    override fun onReserved(slot: ReservedSlot) = SlotStateRto.Reserved
}

fun ReservedSlot.toReservedSlotRto() = ReservedSlotRto(
    slot = toRto(),
    form = form.toRto()
)

fun InvitationRequestRto.toDomain() = InvitationRequest(
    ranges = ranges.map { it.toDateRange() }
)

fun ReserveRequestDto.toRto() = ReserveRequest(
    slotId = SlotId(parseUuid(slotId)),
    form = form.toDomain()
)

fun ReservationForm.toRto() = ReservationFormRto(
    name = name,
    email = email.address
)

fun ReservationFormRto.toDomain() = ReservationForm(
    name = name,
    email = Email(email)
)

fun DateRange.toRto() = DateRangeRto(
    start = start,
    end = end
)

fun DateRangeRto.toDateRange() = DateRange(
    start = start,
    end = end
)
