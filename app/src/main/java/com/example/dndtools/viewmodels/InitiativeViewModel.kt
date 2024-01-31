package com.example.dndtools.viewmodels

import androidx.lifecycle.ViewModel

class InitiativeViewModel: ViewModel() {

    var enemies: Int? = null
    var enemyInitiativeRolls: List<Int> = emptyList()

    var npcs: Int? = null
    var npcsInitiativeRolls: List<Int> = emptyList()

    var characterRolls: MutableList<Int> = mutableListOf()
    fun addCharacterRoll(roll: Int){
        characterRolls.add(roll)
    }
    fun setEnemiesCount(enemiesCount: Int) {
        enemies = enemiesCount
    }
    fun setNpcsCount(npcsCount: Int){
        npcs = npcsCount
    }
    fun generateEnemyRolls() {
        enemies?.let { numberOfEnemies ->
            enemyInitiativeRolls = (1..numberOfEnemies).map {
                (1..20).random()
            }
        }
    }
    fun generateNPCRolls(){
        npcs?.let { numberOfNpcs ->
            npcsInitiativeRolls = (1..numberOfNpcs).map {
                (1..20).random()
            }
        }
    }
}