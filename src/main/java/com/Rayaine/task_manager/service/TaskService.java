package com.Rayaine.task_manager.service;

import com.Rayaine.task_manager.dto.TaskDTO;
import com.Rayaine.task_manager.model.Task;
import com.Rayaine.task_manager.model.User;
import com.Rayaine.task_manager.repository.TaskRepository;
import com.Rayaine.task_manager.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }



    public Long createTask( String username, String taskTitle , String taskDescription , Task.Status currentStatus){
        Task task = new Task();
        task.setUser(userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found " + username)
        ));
        task.setTaskTitle(taskTitle);
        task.setTaskDescription(taskDescription);
        task.setCurrentStatus(currentStatus);
        task.setCreatedAt(LocalDateTime.now());
        if(currentStatus.equals(Task.Status.DONE)){
            task.setFinishedAt(LocalDateTime.now());
        }
        Task savedTask = taskRepository.save(task);
        return savedTask.getId();
    }



    
    public List<TaskDTO> getUserTasks(String username){
        return taskRepository.findByUser(userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found " + username)
        )).stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    public void updateTask( String username , Long taskId , Task.Status currentStatus) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found ")
        );
        if( !task.getUser().getUserName().equals(username)){
            throw new RuntimeException("Unauthorized");
        }
        task.setCurrentStatus(currentStatus);
        if(currentStatus.equals(Task.Status.DONE)){
            task.setFinishedAt(LocalDateTime.now());
        }
        taskRepository.save(task);
    }

    public void deleteTask( String username , Long taskId ) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found ")
        );
        if( !task.getUser().getUserName().equals(username)){
            throw new RuntimeException("Unauthorized");
        }
        taskRepository.delete(task);
    }

}
