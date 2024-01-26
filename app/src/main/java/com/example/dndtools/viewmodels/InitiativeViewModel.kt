package com.example.dndtools.viewmodels

import androidx.lifecycle.ViewModel

class InitiativeViewModel: ViewModel() {
    var enemies: Int? = null
    var enemyInitiativeRolls: List<Int> = emptyList()

    fun setEnemiesCount(enemiesCount: Int) {
        enemies = enemiesCount
    }
    fun generateInitiativeRolls() {
        println("Generate Initiative Rolls: enemies=$enemies")
        enemies?.let { numberOfEnemies ->
            enemyInitiativeRolls = (1..numberOfEnemies).map {
                (1..20).random()
            }
        }
        println("Generate Initiative Rolls: enemyInitiativeRolls=$enemyInitiativeRolls")
    }
}