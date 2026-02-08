package com.example.week_1_domin.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.week_1_domin.model.Task
import com.example.week_4_navikointi.domain.mockTasks

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask

    val addTaskDialogVisible = MutableStateFlow<Boolean>(false)
    val removeTaskDialogVisible = MutableStateFlow<Boolean>(false)



    init {
        _tasks.value = mockTasks
    }

    fun openTask(id: Int) {
        val task = _tasks.value.find { it.id == id }
        _selectedTask.value = task
    }

    fun addTask(task: Task) {
        _tasks.value += task
        addTaskDialogVisible.value = false
    }

    fun toggleDone(id: Int) {
        _tasks.value = _tasks.value.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
    }

    fun removeTask(task: Task) {
        _tasks.value -= task
        removeTaskDialogVisible.value = false
    }

    fun selectTask(task: Task) {
        _selectedTask.value = task
    }

    fun updateTask(updated: Task) {
        _tasks.value = _tasks.value.map {
            if (it.id == updated.id) updated else it
        }
        _selectedTask.value = null
    }

    fun closeDialog() {
        _selectedTask.value = null
    }
}