package com.devtracker.api.entity;

import com.devtracker.api.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;


@Data
@Entity
@Table(name ="task" )
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Task {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID taskId;
    @Column(name = "task_name", nullable = false)
    private String taskName;
    @Column(name = "task_description", nullable = false)
    private String taskDescription;
    @Column(name = "task_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus= TaskStatus.TO_DO;
    @CreatedDate
    @Column(name = "created_at",updatable = false)
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at",updatable = false)
    private Instant updatedAt;
}
