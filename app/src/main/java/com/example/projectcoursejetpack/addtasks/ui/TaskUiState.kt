package com.example.projectcoursejetpack.addtasks.ui

import com.example.projectcoursejetpack.addtasks.ui.model.TasksModel

sealed interface TaskUiState {
    object Loading:TaskUiState
    data class Error(val throwable: Throwable):TaskUiState
    data class Succes(val tasks:List<TasksModel>): TaskUiState
}