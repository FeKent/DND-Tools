package com.example.dndtools.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dndtools.data.DndToolsDatabase

class IntroViewModel : ViewModel() {
   var results: Boolean? = null
}