package sketchboard

class DummyRepo : Repo {

    private val invitations = mutableMapOf<InvitationId, Invitation>()

    override fun create(invitation: Invitation) {
        if (invitations.containsKey(invitation.id)) {
            throw BadRequestException("Invitation already created with ID: ${invitation.id}")
        }
        invitations[invitation.id] = invitation
    }

    override fun update(invitation: Invitation) {
        if (!invitations.containsKey(invitation.id)) {
            throw NotFoundException("Invitation not found by ID: ${invitation.id}")
        }
        invitations[invitation.id] = invitation
    }

    override fun invitationById(id: InvitationId) =
        invitations.entries.find { it.key == id }?.value
            ?: throw NotFoundException("Invitation not found by ID")

    override fun invitationBySlotId(id: SlotId) =
        invitations.entries.find { it.value.slots.any { slot -> slot.id == id } }?.value
            ?: throw NotFoundException("Invitation not found by ID: $id")

    override fun slotById(id: SlotId) =
//        invitations.values.flatMap { it.slots }.firstOrNull { it.id == id }
        invitations.values
            .find { it.slots.any { slot -> slot.id == id } }
            ?.let { it.slots.find { slot -> slot.id == id } }
            ?: throw NotFoundException("Slot not found by ID: $id")

}
