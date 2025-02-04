package com.example.projectcoursejetpack.addtasks.domain

import com.example.projectcoursejetpack.addtasks.data.TaskRepository
import com.example.projectcoursejetpack.addtasks.ui.model.TasksModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke(): Flow<List<TasksModel>> = taskRepository.tasks
}