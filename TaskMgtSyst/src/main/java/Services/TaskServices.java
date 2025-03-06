package Services;

import Dto.TaskDto;
import Entity.Task;
import Entity.User;
import Entity.role;
import Repository.RolesRepository;
import Repository.TaskRepository;
import Repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServices {

    @Autowired
    public TaskRepository taskRepository;
    @Autowired
    public usersRepository userRepository;
    @Autowired
    public RolesRepository rolesRepository;

    public Task createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(long id) {
        taskRepository.delete(id);
    }

    public Task getTaskById(long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task with id: " + id + "not found"));
        return taskRepository.save(task);
    }

    public List<Task> getTasksByUser(String rolename,String username) {
        User user = userRepository.findByName(username).
                orElseThrow(()->new RuntimeException("User with name: " + username + "not found"));

        role role= rolesRepository.findByName(rolename)
                .orElseThrow(()->new RuntimeException("Role with name: " + rolename + "not found"));

                return taskRepository.findByUserAndRole(user, role);

    }

//    public List<Task> getTaskByStatus(String status,String title ) {
//        Task task = taskRepository.findByTaskName(title)
//                .orElseThrow(() -> new RuntimeException("Task with title: " + title + "not found"));
//
//
//
//        Task.Status currentStatus = task.getStatus();
//
//
//        private boolean isValidStatusTransition(Task.Status currrentStatus,Task.Status newStatus){
//
//            return(currentStatus == Task.Status.Todo && newStatus == Task.Status.In_Progress) ||
//                    (currentStatus == Task.Status.In_Progress && newStatus == Task.Status.Done);
//
//        }
//
//        if(!isValidStatusTransition(currentStatus,newSatus))
//
//        }

    }



