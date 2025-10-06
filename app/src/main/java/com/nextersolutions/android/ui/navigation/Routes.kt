package com.nextersolutions.android.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Main : Route()

    @Serializable
    data class ViewAll(val id: String) : Route()

    @Serializable
    data class ViewFood(val id: String) : Route()
}
