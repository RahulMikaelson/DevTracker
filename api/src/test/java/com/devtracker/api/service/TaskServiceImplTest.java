package com.devtracker.api.service;

import com.devtracker.api.dto.TaskRequest;
import com.devtracker.api.dto.TaskResponse;
import com.devtracker.api.dto.UpdateTaskRequest;
import com.devtracker.api.entity.Task;
import com.devtracker.api.enums.TaskStatus;
import com.devtracker.api.exception.TaskNotFoundException;
import com.devtracker.api.mapper.TaskMapper;
import com.devtracker.api.repo.TaskRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Captor
    private ArgumentCaptor<Task> taskCaptor;

    private UUID taskId;
    private Task task;
    private TaskResponse taskResponse;

    @BeforeEach
    void init() {
        taskId = UUID.randomUUID();
        task = new Task();
        task.setTaskId(taskId);
        task.setTaskName("Initial Task");
        task.setTaskDescription("Initial Description");
        task.setTaskStatus(TaskStatus.TO_DO);
        task.setCreatedAt(Instant.now());

        taskResponse = new TaskResponse();
        taskResponse.setTaskId(taskId);
        taskResponse.setTaskName("Initial Task");
        taskResponse.setTaskDescription("Initial Description");
        taskResponse.setTaskStatus(TaskStatus.TO_DO);
    }

    // ------------------------------------------------------------------------------------
    // getAllTasks()
    // ------------------------------------------------------------------------------------
    @Test
    void givenExistingTasks_whenGetAllTasks_thenReturnMappedResponses() {
        when(taskRepo.findAll()).thenReturn(List.of(task));
        when(taskMapper.taskToTaskResponse(task)).thenReturn(taskResponse);

        List<TaskResponse> result = taskService.getAllTasks();

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getTaskId()).isEqualTo(taskId);

        verify(taskRepo, times(1)).findAll();
        verify(taskMapper, times(1)).taskToTaskResponse(task);
        verifyNoMoreInteractions(taskRepo, taskMapper);
    }

    // ------------------------------------------------------------------------------------
    // getTask()
    // ------------------------------------------------------------------------------------
    @Test
    void givenExistingTaskId_whenGetTask_thenReturnTaskResponse() {
        when(taskRepo.findById(taskId)).thenReturn(Optional.of(task));
        when(taskMapper.taskToTaskResponse(task)).thenReturn(taskResponse);

        TaskResponse response = taskService.getTask(taskId);

        assertThat(response).isNotNull();
        assertThat(response.getTaskId()).isEqualTo(taskId);

        verify(taskRepo).findById(taskId);
        verify(taskMapper).taskToTaskResponse(task);
    }

    @Test
    void givenInvalidTaskId_whenGetTask_thenThrowTaskNotFoundException() {
        when(taskRepo.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTask(taskId));

        verify(taskRepo).findById(taskId);
        verifyNoMoreInteractions(taskRepo);
    }

    // ------------------------------------------------------------------------------------
    // createTask()
    // ------------------------------------------------------------------------------------
    @Test
    void givenValidTaskRequest_whenCreateTask_thenSaveAndReturnMappedResponse() {
        TaskRequest request = new TaskRequest();
        request.setTaskName("New Task");
        request.setTaskDescription("New Description");


        when(taskMapper.taskRequestToTask(request)).thenReturn(task);
        when(taskRepo.save(any(Task.class))).thenReturn(task);
        when(taskMapper.taskToTaskResponse(task)).thenReturn(taskResponse);

        TaskResponse response = taskService.createTask(request);

        assertThat(response).isNotNull();
        assertThat(response.getTaskName()).isEqualTo("Initial Task");

        verify(taskMapper).taskRequestToTask(request);
        verify(taskRepo).save(taskCaptor.capture());
        verify(taskMapper).taskToTaskResponse(task);

        Task capturedTask = taskCaptor.getValue();
        assertThat(capturedTask.getTaskName()).isEqualTo("Initial Task");
        assertThat(capturedTask.getTaskStatus()).isEqualTo(TaskStatus.TO_DO);
    }

    // ------------------------------------------------------------------------------------
    // deleteTaskById()
    // ------------------------------------------------------------------------------------
    @Test
    void givenExistingTask_whenDeleteById_thenDeleteIsCalled() {
        when(taskRepo.findById(taskId)).thenReturn(Optional.of(task));

        taskService.deleteTaskById(taskId);

        verify(taskRepo).findById(taskId);
        verify(taskRepo).delete(task);
    }

    @Test
    void givenInvalidTaskId_whenDeleteById_thenThrowException() {
        when(taskRepo.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTaskById(taskId));

        verify(taskRepo).findById(taskId);
        verify(taskRepo, never()).delete(any());
    }

    // ------------------------------------------------------------------------------------
    // updateTask()
    // ------------------------------------------------------------------------------------
    @Test
    void givenValidUpdateRequest_whenUpdateTask_thenFieldsAreUpdatedAndSaved() {
        UpdateTaskRequest updateRequest = new UpdateTaskRequest();
        updateRequest.setTaskName("Updated Task");
        updateRequest.setTaskDescription("Updated Description");
        updateRequest.setTaskStatus(TaskStatus.IN_PROGRESS);

        when(taskRepo.findById(taskId)).thenReturn(Optional.of(task));
        when(taskRepo.save(any(Task.class))).thenReturn(task);
        when(taskMapper.taskToTaskResponse(task)).thenReturn(taskResponse);

        TaskResponse response = taskService.updateTask(taskId, updateRequest);

        assertThat(response).isNotNull();
        verify(taskRepo).findById(taskId);
        verify(taskRepo).save(taskCaptor.capture());
        verify(taskMapper).taskToTaskResponse(task);

        Task capturedTask = taskCaptor.getValue();
        assertThat(capturedTask.getTaskName()).isEqualTo(updateRequest.getTaskName());
        assertThat(capturedTask.getTaskDescription()).isEqualTo(updateRequest.getTaskDescription());
        assertThat(capturedTask.getTaskStatus()).isEqualTo(updateRequest.getTaskStatus());
    }

    @Test
    void givenInvalidId_whenUpdateTask_thenThrowTaskNotFoundException() {
        UpdateTaskRequest updateRequest = new UpdateTaskRequest();
        when(taskRepo.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(taskId, updateRequest));

        verify(taskRepo).findById(taskId);
        verify(taskRepo, never()).save(any());
    }
}
