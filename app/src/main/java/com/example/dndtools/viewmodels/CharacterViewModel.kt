package com.example.dndtools.viewmodels

import androidx.lifecycle.ViewModel

class CharacterViewModel : ViewModel() {
    var characterNames: MutableList<String> = mutableListOf()

    fun addCharacterName(name: String){
        characterNames.add(name)
    }
}