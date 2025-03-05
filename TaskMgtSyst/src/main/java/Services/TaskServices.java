package Services;

import Dto.TaskDto;
import Entity.Task;
import Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServices {

    @Autowired
    public TaskRepository taskRepository;

    public Task createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(long id ){

        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Task with id: " + id + "not found"));
        return taskRepository.save(task);

    }
}
