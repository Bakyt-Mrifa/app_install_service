package kg.megacom.app_install_service.dao;

import kg.megacom.app_install_service.models.entity.StatusEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<StatusEvents, Long> {
    //@Query("select s from StatusEvents s where s.recipientSubsId=?1")
    List<StatusEvents> findByRecipientSubsId(Long recipientSubsId);
}
