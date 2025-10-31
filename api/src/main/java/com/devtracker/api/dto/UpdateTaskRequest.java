package com.devtracker.api.dto;

import com.devtracker.api.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {
    private String taskName;
    private String taskDescription;
    private TaskStatus taskStatus;
}
