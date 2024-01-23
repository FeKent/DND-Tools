package com.example.dndtools.viewmodels

import androidx.lifecycle.ViewModel

class InitiativeViewModel: ViewModel() {
    val enemies: Int? = null
    var enemyInitiativeRolls: List<Int> = emptyList()

    fun generateInitiativeRolls() {
        enemies?.let { numberOfEnemies ->
            enemyInitiativeRolls = (1..numberOfEnemies).map {
                (1..20).random()
            }
        }
    }
}