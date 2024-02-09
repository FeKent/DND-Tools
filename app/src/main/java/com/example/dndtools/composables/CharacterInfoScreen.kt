@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.dndtools.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dndtools.data.Adventure
import com.example.dndtools.data.AdventureType
import com.example.dndtools.data.CharacterProfile
import com.example.dndtools.ui.theme.DNDToolsTheme

enum class CharacterScreenState {
    Add,
    Display,
}

@Composable
fun CharacterInfoScreen(
    adventure: Adventure,
    back: () -> Unit,
    onProfileEntered: (CharacterProfile) -> Unit,
    characters: List<CharacterProfile>
) {
    var currentScreenState by remember { mutableStateOf(CharacterScreenState.Display) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = adventure.title,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .shadow(4.dp)
                .background(color = MaterialTheme.colorScheme.primary),
            navigationIcon = {
                Icon(
                    Icons.Filled.KeyboardArrowLeft,
                    "Back",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            back()
                        }
                )
            }
        )
        Spacer(modifier = Modifier.size(36.dp))

        when (currentScreenState) {
            CharacterScreenState.Display -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            for (i in 1..characters.size) {
                                FilledCharacterCard(characterProfile = characters[i - 1])
                                Spacer(modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                    IconButton(onClick = { currentScreenState = CharacterScreenState.Add }) {
                        Icon(
                            Icons.Filled.Add,
                            "Add Another Profile",
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(80.dp)
                        )
                    }
                }
            }

            CharacterScreenState.Add -> {
                AddCharacterProfile(onProfileEntered = onProfileEntered, adventure = adventure)
            }
        }
    }
}

@Composable
fun AddCharacterProfile(adventure: Adventure, onProfileEntered: (CharacterProfile) -> Unit) {
    var name by remember { mutableStateOf("") }
    var race by remember { mutableStateOf("") }
    var mainClass by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("") }
    var armour by remember { mutableStateOf("") }
    var hitPoints by remember { mutableStateOf("") }
    var spellSave by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        AddTextField(label = "Name", value = name, onValueChange = { name = it })
        AddTextField(label = "Race", value = race, onValueChange = { race = it })
        AddTextField(label = "Main Class", value = mainClass, onValueChange = { mainClass = it })
        AddNumField(label = "Level", value = level, onValueChange = { level = it })
        AddNumField(label = "Armour Class", value = armour, onValueChange = { armour = it })
        AddNumField(label = "Hit Points", value = hitPoints, onValueChange = { hitPoints = it })
        AddNumField(label = "Spell Save DC", value = spellSave, onValueChange = { spellSave = it })
        Spacer(modifier = Modifier.size(32.dp))
        IconButton(onClick = {
            val newCharacterProfile = CharacterProfile(
                name = name,
                race = race,
                mainClass = mainClass,
                level = level.toInt(),
                armour = armour.toInt(),
                hitPoints = hitPoints.toInt(),
                spellSave = spellSave.toInt(),
                adventureId = adventure.id
            )
            onProfileEntered.invoke(newCharacterProfile)
        }) {
            Icon(
                Icons.Filled.Add,
                "Save Profile",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(80.dp)
            )
        }
    }

}

@Composable
fun FilledCharacterCard(characterProfile: CharacterProfile) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .padding(horizontal = 36.dp)
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 14.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = characterProfile.name,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .alignByBaseline(),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, bottom = 6.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = characterProfile.race,
                fontSize = 14.sp,
                modifier = Modifier.alignByBaseline(),
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = ", Lvl ${characterProfile.level} ${characterProfile.mainClass}",
                fontSize = 14.sp,
                modifier = Modifier.alignByBaseline(),
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "AC: ${characterProfile.armour}",
                modifier = Modifier.weight(1f),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "HP: ${characterProfile.hitPoints}",
                modifier = Modifier.weight(1f),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Spell Save: ${characterProfile.spellSave}",
                modifier = Modifier.weight(1f),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun AddProfilePreview() {
//    DNDToolsTheme {
//        AddCharacterProfile(Adventure(1, AdventureType.Campaign, "misfits", 2, "AC"), {})
//    }
//}

//@Preview(showSystemUi = true)
//@Composable
//fun InfoCardPreview() {
//    DNDToolsTheme {
//        FilledCharacterCard(
//            characterProfile = CharacterProfile(
//                "Myra",
//                "Gnome",
//                "Druid",
//                8,
//                16,
//                77,
//                15
//            )
//        )
//    }
//}

@Preview(showSystemUi = true)
@Composable
fun CharacterInfoPreview() {
    DNDToolsTheme {
        CharacterInfoScreen(
            adventure = Adventure(1, AdventureType.Campaign, "Misfits", 4, "Sword Coast"),
            back = {},
            onProfileEntered = {},
            characters = listOf(
                CharacterProfile("Myra", "Gnome", "Druid", 8, 16, 77, 15, 1),
                CharacterProfile("Pipin", "Halfling", "Cleric", 8, 15, 65, 16, 1)
            )
        )
    }
}