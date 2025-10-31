package com.devtracker.api.mapper;

import com.devtracker.api.dto.TaskRequest;
import com.devtracker.api.dto.TaskResponse;
import com.devtracker.api.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task taskRequestToTask(TaskRequest taskRequest);

    TaskResponse taskToTaskResponse(Task task);
}
