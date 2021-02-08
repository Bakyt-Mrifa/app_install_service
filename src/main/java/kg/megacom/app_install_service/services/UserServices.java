package kg.megacom.app_install_service.services;

import kg.megacom.app_install_service.models.responses.Responses;

public interface UserServices {
    Responses inviteUser (Long senderSubsId, Long recipientSubsId);
}
