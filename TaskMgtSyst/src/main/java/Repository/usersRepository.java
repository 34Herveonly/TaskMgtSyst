package Repository;

import Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface usersRepository extends JpaRepository<User,Long> {

    Optional<User> findByName(String username);
    Optional<User> findById(long id);
}
