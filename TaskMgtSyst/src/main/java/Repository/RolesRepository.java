package Repository;

import Entity.roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<roles, Long> {
    static Optional<roles> findByName(String rolename) {
        return null;
    }
}
