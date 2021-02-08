package kg.megacom.app_install_service.models.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recipients")
public class Recipient {
    @Id
    @GeneratedValue
    @Column(name = "recipient_id")
    private Long id;
    private Long subsId;
}

