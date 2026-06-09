package com.Rayaine.task_manager.repository;

import com.Rayaine.task_manager.model.Task;
import com.Rayaine.task_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByUser( User user );
    Optional<Task> findById(Long id);
}
