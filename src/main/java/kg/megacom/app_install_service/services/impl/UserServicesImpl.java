package kg.megacom.app_install_service.services.impl;

import kg.megacom.app_install_service.dao.UserRepository;
import kg.megacom.app_install_service.models.dto.UserDto;
import kg.megacom.app_install_service.models.responses.Responses;
import kg.megacom.app_install_service.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {
     @Autowired
     private UserRepository userRepository;

    @Override
    public Responses inviteUser(Long senderSubsId, Long recipientSubsId) {

        return null;
    }
}
