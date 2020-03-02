package sketchboard

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing

lateinit var domainLogic: DomainLogic

fun Application.myRouting() {
    routing {
        post("/invitations") {
            val invitation = call.receive<InvitationRequestRto>().toDomain()
            val invite = domainLogic.invite(invitation)
            call.respond(invite.toInvitedRto())
        }
        get("/invitations/{invitationId}") {
            val id = call.parameterAsUuid("invitationId")
            val invitation = domainLogic.get(InvitationId(id))
            call.respond(invitation.toInvitationRto())
        }
        // or: post("/invitations/reserve") { ... plus add invitationId in payload?
        post("/invitations/{invitationId}/reserve") {
            val request = call.receive<ReserveRequestDto>().toRto()
            val slot = domainLogic.reserve(request)
            call.respond(slot.toReservedSlotRto())
        }
    }
}
