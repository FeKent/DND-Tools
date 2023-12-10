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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dndtools.data.Campaign
import com.example.dndtools.data.OneShot
import com.example.dndtools.viewmodels.IntroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntroScreen(campaigns: List<Campaign>, oneShots: List<OneShot> ,introViewModel: IntroViewModel = viewModel(), addScreen: (Any?) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedAdventureType by remember { mutableStateOf<String?>(null) }

    Column {
        CenterAlignedTopAppBar(
            title = { Text(text = "Welcome GM!") },
            modifier = Modifier.shadow(4.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            TextButton(
                onClick = { introViewModel.results = true; addScreen(introViewModel.results) },
                modifier = Modifier.background(Color.LightGray)
            ) {
                Text(text = "New Campaign")
            }
            TextButton(
                onClick = { introViewModel.results = false; addScreen(introViewModel.results) },
                modifier = Modifier.background(Color.LightGray)
            ) {
                Text(text = "New One-Shot")
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { newValue -> expanded = newValue }) {
                TextField(
                    value = selectedAdventureType ?: "", onValueChange = {}, readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    placeholder = {
                        Text(text = "Select Adventure Type")
                    }, modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Campaign") },
                        onClick = {
                            selectedAdventureType = "Displaying Campaigns" ;expanded = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = "One-Shot") },
                        onClick = {
                            selectedAdventureType = "Displaying One-Shots" ; expanded = false
                        })
                }
            }
        }
        Spacer(modifier = Modifier.size(24.dp))
        when (selectedAdventureType) {
            "Campaigns" -> campaigns.forEach { item -> CampaignRow(campaign = item) }
            "One-Shots" -> oneShots.forEach { item -> OneShotRow(oneShot = item) }
        }
    }
}

@Composable
fun CampaignRow(campaign: Campaign) {
    Box(modifier = Modifier
        .padding(horizontal = 24.dp)
        .fillMaxWidth()
        .clickable { /*TODO*/ }){
        Row {
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = campaign.title)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = campaign.players.size.toString())
        }
    }
}

@Composable
fun OneShotRow(oneShot: OneShot) {
    Box(modifier = Modifier
        .padding(horizontal = 24.dp)
        .fillMaxWidth()
        .clickable { /*TODO*/ }){
        Row {
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = oneShot.shotTitle)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = oneShot.shotPlayers.toString())
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun InitialPreview() {
//    IntroScreen {}
//}