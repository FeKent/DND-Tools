package com.example.dndtools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.dndtools.composables.AddScreen
import com.example.dndtools.composables.IntroScreen
import com.example.dndtools.data.DndToolsDatabase
import com.example.dndtools.ui.theme.DNDToolsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DNDToolsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DndToolsApp()
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Intro : Screen("intro")
    object Add : Screen("add/{results}")
}

@Composable
fun DndToolsApp() {
    val appContext = LocalContext.current
    val database = remember {
        Room.databaseBuilder(
            appContext,
            DndToolsDatabase::class.java,
            "Database"
        ).build()
    }
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Screen.Intro.route) {
        composable(Screen.Intro.route) {
            val campaigns by database.campaignDao().allCampaigns().collectAsState(initial = emptyList())
            val oneshots by database.oneShotDao().allOneShots().collectAsState(initial = emptyList())
            IntroScreen(campaigns = campaigns, oneShots = oneshots) { results ->
                navController.navigate(
                    "add/$results"
                )
            }
        }
        composable(Screen.Add.route) {
            val addScreenScope = rememberCoroutineScope()
            AddScreen(
                onCampaignEntered = { newCampaign ->
                    addScreenScope.launch {
                        database.campaignDao().insertCampaign(newCampaign)
                    }
                    navController.popBackStack()
                },
                onOneShotEntered = { newOneShot ->
                    addScreenScope.launch {
                        database.oneShotDao().insertOneShot(newOneShot)
                    }
                    navController.popBackStack()
                })
        }
    }
}