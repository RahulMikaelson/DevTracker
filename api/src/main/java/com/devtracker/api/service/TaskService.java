package com.devtracker.api.service;


import com.devtracker.api.dto.TaskRequest;
import com.devtracker.api.dto.TaskResponse;
import com.devtracker.api.dto.UpdateTaskRequest;

import java.util.List;
import java.util.UUID;


public interface TaskService {
    List<TaskResponse> getAllTasks();

    TaskResponse getTask(UUID taskId);

    TaskResponse createTask(TaskRequest taskRequest);

    void deleteTaskById(UUID taskId);


    TaskResponse updateTask(UUID taskId, UpdateTaskRequest updateTaskRequest);
}
