export enum TaskStatus {
    TO_DO = "TO DO",
    IN_PROGRESS = "IN PROGRESS",
    DONE = "DONE"
}

export interface Task {
    taskId: string,
    taskName: string,
    taskDescription: string,
    taskStatus: TaskStatus,
    createdAt: Date,
    updatedAt: Date,
}