package com.example.taskapi.service;

import com.example.taskapi.model.Task;
import com.example.taskapi.model.TaskExecution;
import com.example.taskapi.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task save(Task task) {
        return repository.save(task);
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Optional<Task> findById(String id) {
        return repository.findById(id);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public List<Task> findByName(String name) {
        return repository.findByName(name);
    }

    public Task executeCommand(String id) {
        Optional<Task> optionalTask = repository.findById(id);

        if (optionalTask.isEmpty()) {
            return null; // Controller will return 404
        }

        Task task = optionalTask.get();
        String command = task.getCommand();

        LocalDateTime startTime = LocalDateTime.now();

        try {
            // Detect OS
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder builder;

            if (os.contains("win")) {
                builder = new ProcessBuilder("cmd.exe", "/c", command);
            } else {
                builder = new ProcessBuilder("bash", "-c", command);
            }

            Process process = builder.start();

            // Capture output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Capture errors
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder errorOutput = new StringBuilder();
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            LocalDateTime endTime = LocalDateTime.now();

            String error = exitCode == 0 ? null : errorOutput.toString().trim();

            // Create execution record
            TaskExecution execution = new TaskExecution(startTime, endTime, output.toString().trim(), error);

            // Save execution into task
            task.getTaskExecutions().add(execution);

            return repository.save(task);

        } catch (Exception e) {
            LocalDateTime endTime = LocalDateTime.now();
            TaskExecution execution = new TaskExecution(startTime, endTime, null, "Execution error: " + e.getMessage());
            task.getTaskExecutions().add(execution);
            return repository.save(task);
        }
    }
}
