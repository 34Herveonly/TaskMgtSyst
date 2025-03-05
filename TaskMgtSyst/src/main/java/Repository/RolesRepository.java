package Repository;

import Entity.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<role, Long> {
     Optional<role> findByName(String rolename);



}
