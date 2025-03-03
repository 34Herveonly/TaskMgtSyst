package Entity;

import jakarta.persistence.*;

import javax.management.Notification;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 150)
    private String title;
    @Column(length = 350)
    private String description;
    @Column(length = 150)
    private Status status;
    @Column(length = 150)
    private LocalDateTime dueDate;
    @Column(length = 150)
    @ManyToOne
    private Users assignedTo;
    @Column(length = 150)
    @ManyToOne
    private  Users createdBy;
    @Column(length = 150)
    private LocalDateTime createdAt;
    @Column(length = 150)
    private LocalDateTime updatedAt;


    public enum Status{
        Todo,In_Progress,Done
    }
}
