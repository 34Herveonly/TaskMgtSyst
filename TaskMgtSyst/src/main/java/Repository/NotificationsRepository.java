package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.Notification;

@Repository
public interface NotificationsRepository extends JpaRepository<Notification, Long> {

}
