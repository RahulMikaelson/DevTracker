package com.devtracker.api.dto;

import com.devtracker.api.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private UUID taskId;
    private String taskName;
    private String taskDescription;
    private TaskStatus taskStatus;
    private Instant createdAt;
    private Instant updatedAt;
}
