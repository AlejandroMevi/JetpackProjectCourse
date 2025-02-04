package com.example.projectcoursejetpack.addtasks.domain

import com.example.projectcoursejetpack.addtasks.data.TaskRepository
import com.example.projectcoursejetpack.addtasks.ui.model.TasksModel
import javax.inject.Inject

class AddTasskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(tasksModel: TasksModel){
        taskRepository.add(tasksModel)
    }
}