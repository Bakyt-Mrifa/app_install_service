package kg.megacom.app_install_service.services;

import kg.megacom.app_install_service.models.responses.Responses;

public interface InviteServices {
    Responses inviteUser (Long senderSubsId, Long recipientSubsId);
    Responses getSenderSubsId (Long recipientSubsId);
    Responses getRecipiendHistory (Long recipientSubsId);
}
