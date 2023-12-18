package com.example.dndtools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.dndtools.composables.AddScreen
import com.example.dndtools.composables.IntroScreen
import com.example.dndtools.composables.SelectionScreen
import com.example.dndtools.data.Adventure
import com.example.dndtools.data.AdventureType
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
    object Selection : Screen("selection/{adventureType}/{id}")
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
            val campaigns by database.campaignDao().allCampaigns()
                .collectAsState(initial = emptyList())
            val oneshots by database.oneShotDao().allOneShots()
                .collectAsState(initial = emptyList())
            IntroScreen(
                campaigns = campaigns,
                oneShots = oneshots,
                onShotTap = { oneShot, adventureType ->
                    navController.navigate("selection/$adventureType/${oneShot?.id}")
                },
                onCampaignTap = { campaign, adventureType ->
                    navController.navigate("selection/$adventureType/${campaign?.id}")
                },
                addScreen = { results ->
                    navController.navigate(Screen.Add.route + "/$results")
                }
            )
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
                },
                back = { navController.navigate("intro") })
        }
        composable(Screen.Selection.route) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id")?.toIntOrNull()
            val adventureType = navBackStackEntry.arguments?.getString("adventureType")?.let {
                AdventureType.valueOf(it)
            } ?: AdventureType.Campaign // Default to Campaign if adventureType is null

            var selectedAdventure by remember { mutableStateOf<Adventure?>(null) }

            LaunchedEffect(id, adventureType) {
                if (id != null) {
                    selectedAdventure = if (adventureType == AdventureType.Campaign) {
                        database.campaignDao().getCampaignById(id)
                    } else {
                        database.oneShotDao().getOneShotById(id)
                    }
                }
            }

            SelectionScreen(back = { navController.navigate("intro") }, adventure = selectedAdventure)
        }
    }
}
