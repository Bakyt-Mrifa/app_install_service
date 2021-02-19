package kg.megacom.app_install_service.dao;

import kg.megacom.app_install_service.models.entity.StatusEvents;
import kg.megacom.app_install_service.models.enums.Status;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<StatusEvents, Long> {
    @Query(value = "select * from status_event where recipient_subs_id = ?1 order by event_date desc limit 1", nativeQuery = true)
    StatusEvents getLastEvent(Long id);

    List<StatusEvents> findByRecipientSubsId(Long recipientSubsId);
    StatusEvents findByRecipientSubsIdAndAndStatus(Long id, Status status);

}
