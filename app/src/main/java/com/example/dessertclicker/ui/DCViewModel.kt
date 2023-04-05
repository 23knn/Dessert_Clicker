package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DCViewModel:ViewModel(){
    private val _uiState = MutableStateFlow(DCUIState())
    val uiState : StateFlow<DCUIState> = _uiState.asStateFlow()
    var desserts: List<Dessert> = Datasource.dessertList

    init{
        resetClicker()
    }

    fun resetClicker(){
        _uiState.value = DCUIState(
            0,
            0,
            0,
            5,
            desserts[0].imageId
        )
    }

    fun updatePriceAndSoldCount(){
        // Update the revenue
        // revenue += currentDessertPrice
        // dessertsSold++
        val updatedDessertsSold = _uiState.value.dessertsSold.plus(1)
        val updatedRevenue = _uiState.value.revenue.plus(uiState.value.currentDessertPrice)
        updateDCState(updatedDessertsSold, updatedRevenue)
    }

    fun updateDCState(soldCount: Int, revenue: Int){
        val dessertIndex = determineDessertIndex(soldCount)
        _uiState.update { currentState->
            currentState.copy(
                revenue = revenue,
                dessertsSold = soldCount,
                currentDessertIndex = dessertIndex,
                currentDessertPrice = desserts[dessertIndex].price,
                currentDessertImageId = desserts[dessertIndex].imageId
            )
        }
    }

    fun determineDessertIndex(
        dessertsSold: Int
    ): Int {
        var dessertIndex = 0
        for (index in desserts.indices) {
            if (dessertsSold >= desserts[index].startProductionAmount) {
                dessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }
        return dessertIndex
    }
}