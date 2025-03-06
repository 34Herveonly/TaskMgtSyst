package Entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 150)
    private String title;
    @Column(length = 350)
    private String description;

    @Getter
    private Status status;
    @Column(length = 150)
    private LocalDateTime dueDate;
    @Column(length = 150)
    @ManyToOne
    private User assignedTo;
    @Column(length = 150)
    @ManyToOne
    private User createdBy;
    @Column(length = 150)
    private LocalDateTime createdAt;
    @Column(length = 150)
    private LocalDateTime updatedAt;

    public void setTitle(String title) {
    }

    public Status getStatus(Status status) {
        return this.status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status{
        Todo,In_Progress,Done
    }
}
