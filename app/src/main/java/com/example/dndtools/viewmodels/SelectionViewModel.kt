package com.example.dndtools.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dndtools.data.Campaign
import com.example.dndtools.data.DndToolsDatabase
import com.example.dndtools.data.OneShot
import kotlinx.coroutines.launch

class SelectionViewModel(
    savedStateHandle: SavedStateHandle,
    private val database: DndToolsDatabase
) : ViewModel() {

    private var campaignId: Int? = savedStateHandle.get<String>("campaignId")?.toInt()
    private var oneShotId: Int? = savedStateHandle.get<String>("oneShotId")?.toInt()

    // LiveData to observe changes in the campaign
    private val _campaign = MutableLiveData<Campaign>()
    val campaign: LiveData<Campaign> get() = _campaign

    // LiveData to observe changes in the one shot
    private val _oneShot = MutableLiveData<OneShot>()
    val oneShot: LiveData<OneShot> get() = _oneShot

    init {
        // Fetch the campaign and one shot when the ViewModel is created
        campaignId?.let { fetchCampaignById(it) }
        oneShotId?.let { fetchOneShotById(it) }
    }

    private fun fetchCampaignById(id: Int) {
        viewModelScope.launch {
            val campaign = database.campaignDao().getCampaignById(id)
            _campaign.value = campaign
        }
    }

    private fun fetchOneShotById(id: Int) {
        viewModelScope.launch {
            val oneShot = database.oneShotDao().getOneShotById(id)
            _oneShot.value = oneShot
        }
    }
}