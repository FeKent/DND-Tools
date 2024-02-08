@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.dndtools.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dndtools.data.Adventure
import com.example.dndtools.data.CharacterProfile
import com.example.dndtools.ui.theme.DNDToolsTheme

@Composable
fun CharacterInfoScreen(adventure: Adventure?, back: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = adventure?.title.toString(),
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
        Spacer(modifier = Modifier.size(52.dp))
    }
}


@Composable
fun CharacterCard(characterProfile: CharacterProfile) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .padding(36.dp)
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 14.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = characterProfile.name,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                modifier = Modifier
                    .alignByBaseline()
                    .weight(1f),
                color = MaterialTheme.colorScheme.onBackground
            )
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

@Preview(showSystemUi = true)
@Composable
fun InfoCardPreview() {
    DNDToolsTheme {
        CharacterCard(characterProfile = CharacterProfile("Myra", "Gnome", "Druid", 8, 16, 77, 15))
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun CharacterInfoPreview() {
//    DNDToolsTheme {
//        CharacterInfoScreen(
//            adventure = Adventure(
//                1,
//                AdventureType.OneShot,
//                "Misfits",
//                3,
//                "Sword's Coast"
//            )){}
//    }
//}