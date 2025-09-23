package com.example.taskapi.controller;

import com.example.taskapi.model.Task;
import com.example.taskapi.model.TaskExecution;
import com.example.taskapi.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    //  Create a new task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return service.save(task);
    }

    //  Get all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return service.findAll();
    }

    //  Get a task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  Update an existing task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task task) {
        return service.findById(id)
                .map(existing -> {
                    existing.setName(task.getName());
                    existing.setCommand(task.getCommand());
                    existing.setDescription(task.getDescription());
                    existing.setOwner(task.getOwner());
                    Task updated = service.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //  Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //  Search tasks by name
    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam String name) {
        return service.findByName(name);
    }

    //  Execute a task by ID
    @PutMapping("/{id}/execute")
    public ResponseEntity<Task> executeTask(@PathVariable String id) {
        Task updatedTask = service.executeCommand(id);
        if (updatedTask == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTask);
    }

}
