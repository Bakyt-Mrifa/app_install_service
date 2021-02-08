package kg.megacom.app_install_service.models.entity;

import kg.megacom.app_install_service.models.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private Long senderSubsId;
    private Long recipientSubsId;
    private Status status;
}
