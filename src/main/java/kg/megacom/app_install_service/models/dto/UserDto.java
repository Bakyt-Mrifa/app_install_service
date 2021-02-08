package kg.megacom.app_install_service.models.dto;

import kg.megacom.app_install_service.models.enums.Status;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private Long senderSubsId;
    private Long recipientSubsId;
    private Status status;
}
