package Dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NotNull
public class TaskDto {

    private long taskId;
    private String title;
    private String taskDescription;
    private String taskStatus;
    private String dueDate;
    private String assignedTo;
    private String createdBy;
    private String createdAt;
    private String updatedAt;

}
