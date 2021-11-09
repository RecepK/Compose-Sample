package com.kurban.recep.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kurban.recep.common.Constants
import com.kurban.recep.common.Screen
import com.kurban.recep.data.model.response.DataModel
import com.kurban.recep.presentation.screen.detail.DetailScreen
import com.kurban.recep.presentation.screen.main.MainScreen
import com.kurban.recep.presentation.ui.theme.ITunesAPITheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITunesAPITheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    ComposableNavHost(navController = navController)
                }
            }
        }
    }
}

@Composable
fun ComposableNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.name
    ) {
        composable(route = Screen.MainScreen.name) {
            MainScreen { model ->
                navController.currentBackStackEntry?.arguments =
                    Bundle().apply {
                        putParcelable(Constants.BUNDLE_KEY_FOR_DATA, model)
                    }

                navController.navigate(route = Screen.DetailScreen.name)
            }
        }
        composable(route = Screen.DetailScreen.name) {
            val model = navController
                .previousBackStackEntry?.arguments?.getParcelable<DataModel>(Constants.BUNDLE_KEY_FOR_DATA)
            DetailScreen(data = model) {
                navController.popBackStack()
            }
        }
    }
}