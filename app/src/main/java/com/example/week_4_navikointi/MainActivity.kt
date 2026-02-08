package com.example.week_4_navikointi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.week_1_domin.view.HomeScreen
import com.example.week_1_domin.viewmodel.TaskViewModel
import navigation.ROUTE_CALENDAR
import navigation.ROUTE_HOME
import view.CalendarScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel:  TaskViewModel = viewModel()

            NavHost(
                navController = navController,
                startDestination = ROUTE_HOME
            ){
                composable (ROUTE_HOME){
                    HomeScreen(
                        viewModel = viewModel,
                        onTaskClick = {id ->
                            viewModel.openTask(id)
                        },
                        onAddClick = {
                            viewModel.addTaskDialogVisible.value = true
                        },
                        onRemoveClick = {
                            viewModel.removeTaskDialogVisible.value = true
                        },
                        onNavigateCalendar = {
                            navController.navigate(ROUTE_CALENDAR)
                        }
                    )
                }
                composable(ROUTE_CALENDAR){
                    CalendarScreen(
                        viewModel = viewModel,
                        onTaskClick = { id ->
                            viewModel.openTask(id)
                        },
                        onNavigateHome = {
                            navController.navigate(ROUTE_HOME)
                        }
                    )
                }
            }
        }
    }
}
