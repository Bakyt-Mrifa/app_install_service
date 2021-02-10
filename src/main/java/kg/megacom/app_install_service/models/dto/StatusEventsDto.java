package kg.megacom.app_install_service.models.dto;

import kg.megacom.app_install_service.models.enums.Status;
import lombok.Data;

import java.util.Date;

@Data
public class StatusEventsDto {

    private Long id;
    private Long senderSubsId;
    private Long recipientSubsId;
    private Date eventDate;
    private Status status;

}
