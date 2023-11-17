package dev.virunarala.gweiland.home.data.model

data class ListingUiModel(
    val id: String,
    val name: String,
    val symbol: String,
    val usdPrice: Double,
    val dailyChange: Double,
    val logo: String
)
