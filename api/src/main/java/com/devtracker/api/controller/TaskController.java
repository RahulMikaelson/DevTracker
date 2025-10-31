package com.devtracker.api.controller;

import com.devtracker.api.dto.TaskRequest;
import com.devtracker.api.dto.TaskResponse;
import com.devtracker.api.dto.UpdateTaskRequest;
import com.devtracker.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable UUID taskId){
        System.out.println(taskId);
        return new ResponseEntity<>(taskService.getTask(taskId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest){
        return  new ResponseEntity<>(taskService.createTask(taskRequest),HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable UUID taskId,
            @RequestBody UpdateTaskRequest updateTaskRequest
            ) {
        TaskResponse updatedTask = taskService.updateTask(taskId, updateTaskRequest);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable UUID taskId){
        taskService.deleteTaskById(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
