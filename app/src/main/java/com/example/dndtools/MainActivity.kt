package com.example.dndtools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.dndtools.composables.AddScreen
import com.example.dndtools.composables.IntroScreen
import com.example.dndtools.composables.SelectionScreen
import com.example.dndtools.data.Campaign
import com.example.dndtools.data.DndToolsDatabase
import com.example.dndtools.data.OneShot
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
    object Selection : Screen("selection/{oneShotId?}/{campaignId?}")
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
                onShotTap = { oneShot -> navController.navigate("selection/${oneShot.id}") },
                onCampaignTap = { campaign -> navController.navigate("selection/${campaign.id}") },
                addScreen = { results -> navController.navigate("add/$results") })
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
        composable(Screen.Selection.route, arguments = listOf(
            navArgument("oneShotId") { type = NavType.IntType },
            navArgument("campaignId") { type = NavType.IntType }
        )) { backStackEntry ->
            val arguments = backStackEntry.arguments
            val oneShotId = arguments?.getInt("oneShotId")
            val campaignId = arguments?.getInt("campaignId")

            if(oneShotId != null){
                var oneShot: OneShot? by remember {mutableStateOf(null)}

                LaunchedEffect(key1 = Unit){
                    oneShot = database.oneShotDao().getOneShotById(oneShotId)
                }

                oneShot?.let { SelectionScreen(
                    campaign = null,
                    oneShot = it,
                    back = { navController.navigate("add") }
                ) }?: run {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(150.dp),
                            strokeWidth = 8.dp
                        )
                    }
                }
            } else if (campaignId != null) {
                var campaign: Campaign? by remember {mutableStateOf(null)}

                LaunchedEffect(key1 = Unit){
                    campaign = database.campaignDao().getCampaignById(campaignId)
                }

                campaign?.let { SelectionScreen(
                    campaign = it,
                    oneShot = null,
                    back = { navController.navigate("add") }
                ) }?: run {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(150.dp),
                            strokeWidth = 8.dp
                        )
                    }
                }
            }
        }
    }
}
