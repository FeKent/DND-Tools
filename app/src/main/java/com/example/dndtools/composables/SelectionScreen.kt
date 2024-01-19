@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dndtools.data.Adventure
import com.example.dndtools.data.AdventureType
import com.example.dndtools.ui.theme.DNDToolsTheme
import com.example.dndtools.ui.theme.light1

@Composable
fun SelectionScreen(back: () -> Unit, adventure: Adventure?, initiativeScreen: () -> Unit) {
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
        Spacer(modifier = Modifier.size(32.dp))
        Text(
            text = "Tools", textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold,
            color = light1, fontSize = 20.sp, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        Divider(modifier = Modifier.padding(horizontal = 100.dp), color = light1)
        Spacer(modifier = Modifier.size(16.dp))
        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Column {
                Row(horizontalArrangement = Arrangement.SpaceBetween)
                {
                    IconWithCaption(Icons.Filled.AccountCircle, "Player Info", onClick = {})
                    Spacer(modifier = Modifier.size(24.dp))
                    IconWithCaption(Icons.Filled.DateRange, "Schedule Sessions", onClick = {})
                    Spacer(modifier = Modifier.size(24.dp))
                    IconWithCaption(
                        Icons.Filled.Warning,
                        "Initiative Tracker",
                        onClick = { initiativeScreen() })
                }
                Spacer(modifier = Modifier.size(24.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    IconWithCaption(Icons.Filled.AddCircle, "Add Notes", onClick = {})
                    Spacer(modifier = Modifier.size(24.dp))
                    IconWithCaption(Icons.Filled.Create, "Edit Info", onClick = {})
                    Spacer(modifier = Modifier.size(24.dp))
                    IconWithCaption(Icons.Filled.Delete, "Delete", onClick = {})
                }
            }
        }
    }
}

@Composable
fun IconWithCaption(
    icon: ImageVector,
    caption: String,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.clickable { onClick() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                icon,
                contentDescription = null, // Use contentDescription from Text
                modifier = Modifier.size(50.dp)
            )
            Text(
                caption,
                textAlign = TextAlign.Center,
                maxLines = 2, // Set the maximum number of lines to 2
                overflow = TextOverflow.Ellipsis, // Add ellipsis for long text
                modifier = Modifier.width(80.dp)
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun SelectionPreview() {
    DNDToolsTheme {
        SelectionScreen(back = {}, adventure = Adventure(1, AdventureType.OneShot,"Misfits", 3, "Sword's Coast"), {})
    }
}