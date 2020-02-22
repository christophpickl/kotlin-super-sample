package sketchboard

interface DomainLogic {
    fun invite(request: InvitationRequest): Invitation
    fun get(id: InvitationId): Invitation
    fun reserve(request: ReserveRequest): ReservedSlot
}

interface Repo {
    fun slotById(id: SlotId): Slot
    fun invitationById(id: InvitationId): Invitation
    fun invitationBySlotId(id: SlotId): Invitation
    fun create(invitation: Invitation)
    fun update(invitation: Invitation)
}

class DomainLogicImpl(
    private val repo: Repo
) : DomainLogic {

    override fun invite(request: InvitationRequest): Invitation {
        val invitation = Invitation.newByRequest(request)
        repo.create(invitation)
        return invitation
    }

    override fun get(id: InvitationId) =
        repo.invitationById(id)

    override fun reserve(request: ReserveRequest): ReservedSlot {
        val slot = repo.slotById(request.slotId) as? AvailableSlot
            ?: throw SlotNotAvailableException("Can not reserve slot: ${request.slotId}")
        val invitation = repo.invitationBySlotId(slot.id)

        val reservedSlot = slot.reserve(request.form)
        val updatedInvitation = invitation.replace(reservedSlot)
        repo.update(updatedInvitation)

        return reservedSlot
    }
}
