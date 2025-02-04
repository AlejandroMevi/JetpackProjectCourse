package com.example.projectcoursejetpack.addtasks.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectcoursejetpack.addtasks.domain.AddTasskUseCase
import com.example.projectcoursejetpack.addtasks.domain.DeleteTaskUseCase
import com.example.projectcoursejetpack.addtasks.domain.GetTasksUseCase
import com.example.projectcoursejetpack.addtasks.domain.UpdateTaskUseCase
import com.example.projectcoursejetpack.addtasks.ui.TaskUiState.Error
import com.example.projectcoursejetpack.addtasks.ui.TaskUiState.Succes
import com.example.projectcoursejetpack.addtasks.ui.model.TasksModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTasskUseCase: AddTasskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    val uiState: StateFlow<TaskUiState> = getTasksUseCase().map(::Succes)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TaskUiState.Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTasksCreated(task: String) {
        _showDialog.value = false
        viewModelScope.launch {
            addTasskUseCase(TasksModel(task = task))
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(tasksModel: TasksModel) {
        viewModelScope.launch {
            updateTaskUseCase(tasksModel.copy(selected = !tasksModel.selected))
        }

    }

    fun onItemRemoved(tasksModel: TasksModel) {
        viewModelScope.launch {
            deleteTaskUseCase(tasksModel)
        }
    }

}