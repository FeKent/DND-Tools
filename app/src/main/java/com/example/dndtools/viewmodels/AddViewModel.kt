package com.example.dndtools.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class AddViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    var results: Boolean = savedStateHandle.get<String>("results")!!.toBoolean()
}