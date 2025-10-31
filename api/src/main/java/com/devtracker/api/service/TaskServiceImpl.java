package com.devtracker.api.service;

import com.devtracker.api.dto.TaskRequest;
import com.devtracker.api.dto.TaskResponse;
import com.devtracker.api.dto.UpdateTaskRequest;
import com.devtracker.api.entity.Task;
import com.devtracker.api.exception.TaskNotFoundException;
import com.devtracker.api.mapper.TaskMapper;
import com.devtracker.api.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements  TaskService{
    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;

    private Task getTaskById(UUID taskId){
        return taskRepo.findById(taskId).orElseThrow(()->new TaskNotFoundException("Task Not Found with Id: "+taskId,"TASK_NOT_FOUND"));
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepo.findAll().stream().map(taskMapper::taskToTaskResponse).toList();
    }

    @Override
    public TaskResponse getTask(UUID taskId) {
        return taskMapper.taskToTaskResponse(getTaskById(taskId));
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        return taskMapper.taskToTaskResponse(taskRepo.save(taskMapper.taskRequestToTask(taskRequest)));
    }

    @Override
    public void deleteTaskById(UUID taskId) {
        Task task = getTaskById(taskId);
        log.info("Deleting task: {}", task);
        taskRepo.delete(task);
    }

    @Override
    public TaskResponse updateTask(UUID taskId, UpdateTaskRequest updateTaskRequest) {
        Task task = getTaskById(taskId);
        log.info("Updating task: {}",task);
        task.setTaskName(updateTaskRequest.getTaskName());
        task.setTaskDescription(updateTaskRequest.getTaskDescription());
        task.setTaskStatus(updateTaskRequest.getTaskStatus());
        return taskMapper.taskToTaskResponse(taskRepo.save(task));
    }


}
