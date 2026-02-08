package view

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.week_1_domin.model.Task

@Composable
fun RemoveDialog(
    task: Task,
    onClose: () -> Unit,
    onRemoveConfirmed: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onClose,
        title = { Text("Remove Task") },
        text = {
            Text("Are you sure you want to remove the task \"${task.title}\"?")
        },
        confirmButton = {
            Button(onClick = onRemoveConfirmed) { // Only invokes the lambda
                Text("Remove")
            }
        },
        dismissButton = {
            Button(onClick = onClose) {
                Text("Cancel")
            }
        }
    )
}