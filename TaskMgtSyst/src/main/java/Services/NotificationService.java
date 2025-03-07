package Services;

import Entity.Task;
import Entity.User;
import Repository.NotificationsRepository;
import Repository.RolesRepository;
import Repository.TaskRepository;
import Repository.usersRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final JavaMailSender mailSender;

    public NotificationService(NotificationsRepository notificationsRepository,
                               usersRepository userRepository,
                               TaskRepository taskRepository,
                               JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendTaskAssignedNotification(User recipient, Task task) {
        if (recipient == null || recipient.getEmail() == null) {
            throw new IllegalArgumentException("Recipient email is required!");
        }

        String subject = "New Task Assigned: " + task.getTitle();
        String message = "Hello " + recipient.getUsername() + ",\n\n"
                + "You have been assigned a new task: " + task.getTitle() + ".\n"
                + "Description: " + task.getDescription() + "\n"
                + "Due Date: " + task.getDueDate() + "\n\n"
                + "Please check your task list for details.";

        sendEmail(recipient.getEmail(), subject, message);
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
