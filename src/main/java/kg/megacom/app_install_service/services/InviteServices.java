package kg.megacom.app_install_service.services;

import kg.megacom.app_install_service.models.entity.StatusEvents;
import kg.megacom.app_install_service.models.responses.Responses;
import org.springframework.http.ResponseEntity;

public interface InviteServices {
    Responses inviteUser (Long senderSubsId, Long recipientSubsId);
    Responses getSenderSubsId (Long recipientSubsId);
    Responses getRecipientHistory (Long recipientSubsId);
    ResponseEntity<?> invite (Long senderSubsId, Long recipientSubsId);



    Object userInvite (Long senderSubsId, Long recipientSubsId);


}
