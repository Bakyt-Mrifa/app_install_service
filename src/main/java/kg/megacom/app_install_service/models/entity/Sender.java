package kg.megacom.app_install_service.models.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "senders")
public class Sender {

    @Id
    @GeneratedValue
    @Column(name = "sender_id")
    private Long id;
    private Long subsId;
}
