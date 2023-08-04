package com.pou.udfsample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pou.udfsample.ui.main.FruitsListScreen
import com.pou.udfsample.ui.main.MainViewModel
import com.pou.udfsample.ui.main.MainViewModelFactory
import com.pou.udfsample.ui.main.ServiceLocator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "FruitsListScreen") {
                composable("FruitsListScreen") {
                    val viewModel = viewModel<MainViewModel>(
                        factory = MainViewModelFactory(
                            ServiceLocator.repository
                        )
                    )
                    FruitsListScreen(navigation = navController, viewModel)
                }
                /*composable("SecondScreen") {
                    SecondScreen(navigation = navController)
                }*/
            }
        }
    }
}