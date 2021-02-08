package kg.megacom.app_install_service.models.entity;

import kg.megacom.app_install_service.models.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "status_event")
public class StatusEvents {

    @Id
    @GeneratedValue
    @Column(name = "status_event_id")
    private Long id;
    private Long senderSubsId;
    private Long recipientSubsId;
    private Date eventData;
    private Status status;

}
