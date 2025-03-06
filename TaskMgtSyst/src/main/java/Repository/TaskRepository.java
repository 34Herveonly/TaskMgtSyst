package Repository;

import Entity.Task;
import Entity.User;
import Entity.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTaskName(String taskName);
    Optional<Task> findById(Long taskId);
    void delete(long id);
    Optional<User> findByUserName(String userName);
    List<Task> findByUserAndRole(User user, role role);
    String findTaskByStatus(Task.Status status );

}
