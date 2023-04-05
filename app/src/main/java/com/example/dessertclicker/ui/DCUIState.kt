package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes

data class DCUIState(
    val revenue: Int,
    val desertsSold: Int,
    val currentDessertIndex: Int,
    val currentDessertPrice: Int,
    @DrawableRes val currentDessertImageId: Int
)
