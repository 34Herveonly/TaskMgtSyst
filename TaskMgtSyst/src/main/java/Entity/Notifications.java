package Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.AccessType;
import org.springframework.scheduling.config.Task;

import java.sql.Date;

@Getter
@Setter

@Table(name = "notifications")
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column

    @ManyToOne
    private Users user;
    @Column(length = 450)
    private String message;
    @Column(length = 450)
    private Date created_at;
    @Column(length = 450)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true) // Optional reference
    private Task task;

    public enum Status{
        Sent,Pending,Failed,;
    }

}
