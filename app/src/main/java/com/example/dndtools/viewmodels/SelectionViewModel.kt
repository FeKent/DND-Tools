package com.example.dndtools.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SelectionViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    var campaignId: Int? = savedStateHandle.get<String>("campaignId")!!.toInt()
    var oneShotId: Int? = savedStateHandle.get<String>("oneShotId")!!.toInt()
}