package com.Rayaine.task_manager.dto;

import com.Rayaine.task_manager.model.Task;

import java.time.LocalDateTime;

public class TaskDTO {
    private Long id;
    private String taskTitle;
    private String taskDescription;
    private String currentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;


    public TaskDTO(Long id, String taskTitle, String taskDescription, String currentStatus, LocalDateTime createdAt, LocalDateTime finishedAt){
        this.id = id;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.currentStatus = currentStatus;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
    }

    public TaskDTO( Task task ){
        this.id = task.getId();
        this.taskTitle = task.getTaskTitle();
        this.taskDescription = task.getTaskDescription();
        this.currentStatus = String.valueOf(task.getCurrentStatus());
        this.createdAt = task.getCreatedAt();
        this.finishedAt = task.getFinishedAt();
    }


    public Long getId() {
        return id;
    }


    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }
}
