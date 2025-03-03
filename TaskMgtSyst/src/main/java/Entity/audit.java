package Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "audit")
public class audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 200)
    private String actionType;
    @Column(length = 200)
    private String entityName;
    @Column(length = 200)
    private long entityId;
    @Column(length = 200)
    private Users user;
    @Column
    private LocalDateTime timeStamp;
}
