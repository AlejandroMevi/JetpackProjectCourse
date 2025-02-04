package com.example.projectcoursejetpack.addtasks.domain

import com.example.projectcoursejetpack.addtasks.data.TaskRepository
import com.example.projectcoursejetpack.addtasks.ui.model.TasksModel
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(tasksModel: TasksModel){
        taskRepository.update(tasksModel)
    }
}