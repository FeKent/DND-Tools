package com.example.dndtools.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dndtools.data.Campaign
import com.example.dndtools.data.OneShot
import com.example.dndtools.ui.theme.DNDToolsTheme
import com.example.dndtools.ui.theme.cambridge
import com.example.dndtools.ui.theme.light1
import com.example.dndtools.viewmodels.IntroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntroScreen(
    campaigns: List<Campaign>,
    oneShots: List<OneShot>,
    introViewModel: IntroViewModel = viewModel(),
    addScreen: (Any?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedAdventureType by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Welcome GM!",
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .shadow(4.dp)
                .background(color = MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            TextButton(
                onClick = { introViewModel.results = true; addScreen(introViewModel.results) },
                modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
            ) {
                Text(text = "New Campaign")
            }
            TextButton(
                onClick = { introViewModel.results = false; addScreen(introViewModel.results) },
                modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
            ) {
                Text(text = "New One-Shot")
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { newValue -> expanded = newValue },
                modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
            ) {
                TextField(
                    value = selectedAdventureType ?: "", onValueChange = {}, readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    placeholder = {
                        Text(
                            text = "Select Adventure Type",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }, modifier = Modifier.menuAnchor(), colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.onPrimary,
                        focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                        unfocusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.primary,
                    ),
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(cambridge)
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Campaign",
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        onClick = {
                            selectedAdventureType = "Displaying Campaigns";expanded = false
                        })
                    Divider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "One-Shot",
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        onClick = {
                            selectedAdventureType = "Displaying One-Shots"; expanded = false
                        })
                    Divider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Both",
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        onClick = {
                            selectedAdventureType = "Select Adventure Type"; expanded = false
                        })
                }
            }
        }
        Spacer(modifier = Modifier.size(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp)
                .padding(bottom = 10.dp)
        ) {
            Text(
                text = "Title",
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 16.dp),
                fontWeight = FontWeight.SemiBold,
                color = light1
            )
            Text(
                text = "Players",
                modifier = Modifier.weight(2f),
                textAlign = TextAlign.End,
                fontWeight = FontWeight.SemiBold,
                color = light1
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (selectedAdventureType?.contains("One-Shots") == true) {
                oneShots.forEach { item -> OneShotRow(oneShot = item) }
            } else if (selectedAdventureType?.contains("Campaign") == true) {
                campaigns.forEach { item -> CampaignRow(campaign = item) }
            } else {
                oneShots.forEach { item -> OneShotRow(oneShot = item) }
                campaigns.forEach { item -> CampaignRow(campaign = item) }
            }
            Divider(modifier = Modifier.padding(horizontal = 32.dp))
        }
    }
}

@Composable
fun CampaignRow(campaign: Campaign) {
    Box(modifier = Modifier
        .padding(horizontal = 32.dp)
        .fillMaxWidth()
        .clickable { /*TODO*/ }) {
        Row(modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)) {
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = campaign.title,
                modifier = Modifier
                    .align(CenterVertically)
                    .weight(2f),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = campaign.players.size.toString(),
                modifier = Modifier
                    .align(CenterVertically)
                    .weight(0.5f),
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Divider()
    }
}

@Composable
fun OneShotRow(oneShot: OneShot) {
    Box(modifier = Modifier
        .padding(horizontal = 32.dp)
        .fillMaxWidth()
        .clickable { /*TODO*/ }
    ) {
        Row(modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)) {
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = oneShot.shotTitle,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .align(CenterVertically)
                    .weight(2f),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = oneShot.shotPlayers.toString(),
                modifier = Modifier
                    .align(CenterVertically)
                    .weight(0.5f),
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Divider()
    }
}

@Preview(showSystemUi = true/*, uiMode = UI_MODE_NIGHT_YES*/)
@Composable
fun InitialPreview() {
    val exampleCamps = listOf(
        Campaign(
            id = 1,
            title = "Misfits of Fire and Dice",
            players = arrayOf("Fiona", "Cip"),
            characters = arrayOf("Myra", "Pipin"),
            setting = "Sword's Coast"
        ), Campaign(
            id = 1,
            title = "Once and Again",
            players = arrayOf("Snippy", "Elise", "Alex", "Sam"),
            characters = arrayOf("Kitt", "Milee", "Gallifrey", "Wyrm"),
            setting = "Verulien"
        )
    )
    val exampleOneShot = listOf(
        OneShot(
            id = 1,
            shotTitle = "Moon Over Graymoor",
            shotPlayers = 3,
            shotSetting = "Sword's Coast"
        )
    )
    DNDToolsTheme {
        IntroScreen(campaigns = exampleCamps, oneShots = exampleOneShot) {}
    }
}