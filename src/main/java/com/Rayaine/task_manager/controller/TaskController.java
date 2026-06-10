package com.Rayaine.task_manager.controller;


import com.Rayaine.task_manager.model.Task;
import com.Rayaine.task_manager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/api/tasks")
    public ResponseEntity<?> createTask(@RequestBody Map<String, String> request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long taskId = taskService.createTask(username, request.get("taskTitle"), request.get("taskDescription"), Task.Status.valueOf(request.get("status")));
        Map<String, Object> response = new HashMap<>();
        response.put("id", taskId);
        response.put("taskTitle", request.get("taskTitle"));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/api/tasks")
    public ResponseEntity<?> getUserTasks() {
        List<Task> tasks = taskService.getUserTasks(SecurityContextHolder.getContext().getAuthentication().getName());

        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/api/tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id ,@RequestBody Map<String, String> request) {
        taskService.updateTask(SecurityContextHolder.getContext().getAuthentication().getName(),id, Task.Status.valueOf(request.get("status")));
        Map<String, Long> response = new HashMap<>();
        response.put("id", id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(SecurityContextHolder.getContext().getAuthentication().getName(), id);
        return ResponseEntity.noContent().build();
    }

}