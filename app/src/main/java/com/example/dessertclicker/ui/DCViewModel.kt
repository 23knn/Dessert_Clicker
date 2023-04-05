package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DCViewModel:ViewModel(){
    private val _uiState = MutableStateFlow(DCUIState())
    val uiState : StateFlow<DCUIState> = _uiState.asStateFlow()
}