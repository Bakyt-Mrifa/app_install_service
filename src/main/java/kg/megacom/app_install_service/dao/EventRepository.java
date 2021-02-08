package kg.megacom.app_install_service.dao;

import kg.megacom.app_install_service.models.entity.StatusEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<StatusEvents, Long> {
}
