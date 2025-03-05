package Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String rolename;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>(); // ✅ Corrected to use Set<Users>

}
