package kg.megacom.app_install_service.services;

import kg.megacom.app_install_service.models.dto.UserDto;

public interface UserServices {
    UserDto inviteUser (UserDto userDto);
}
