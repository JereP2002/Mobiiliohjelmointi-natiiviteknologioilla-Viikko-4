package com.example.week_1_domin.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.week_1_domin.viewmodel.TaskViewModel
import view.AddDialog
import view.RemoveDialog



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: TaskViewModel,
    onTaskClick: (Int) -> Unit = {},
    onAddClick: () -> Unit = {},
    onNavigateCalendar: () -> Unit = {},
    onRemoveClick: () -> Unit

) {
    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()
    val addTaskFlag by viewModel.addTaskDialogVisible.collectAsState()
    val removeTaskFlag by viewModel.removeTaskDialogVisible.collectAsState()


    Column(modifier = Modifier.padding(16.dp)) {

        TopAppBar(
            title = { Text("Task List") },
            actions = {
                IconButton(onClick = onNavigateCalendar) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "Go to calendar"
                    )
                }
            }
        )

        Row {
            Button(
                onClick = onAddClick,
                modifier = Modifier.padding(8.dp),
            ) {
                Text("Add Task")
            }


            Button(
                onClick = onRemoveClick,
                modifier = Modifier.padding(8.dp),
            ) {
                Text("Delete Task")
            }
        }

        LazyColumn {
            items(tasks) { task ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { onTaskClick(task.id) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                task.title,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(task.description)
                        }
                        Checkbox(
                            checked = task.done,
                            onCheckedChange = { viewModel.toggleDone(task.id) }
                        )
                    }
                }
            }
        }
    }


    if (selectedTask != null) {
        DetailDialog(
            task = selectedTask!!,
            onClose = { viewModel.closeDialog() },
            onUpdate = { viewModel.updateTask(it) }
        )
    }

    if (addTaskFlag) {
        AddDialog(
            onClose = { viewModel.addTaskDialogVisible.value = false },
            onUpdate = { task ->
                viewModel.addTask(task)
                viewModel.addTaskDialogVisible.value = false
            }
        )
    }
    if (removeTaskFlag && selectedTask != null) {
        RemoveDialog(
            task = selectedTask!!,
            onClose = { viewModel.removeTaskDialogVisible.value = false },
            onRemoveConfirmed = {
                viewModel.removeTask(selectedTask!!)
                viewModel.removeTaskDialogVisible.value = false
            }
        )
    }
}