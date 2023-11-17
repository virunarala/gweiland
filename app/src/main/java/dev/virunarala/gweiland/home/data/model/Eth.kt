package dev.virunarala.gweiland.home.data.model

import com.squareup.moshi.Json

data class Eth(
    val price: Double,
    @Json(name = "volume_24h")
    val volume24h: Double,
    @Json(name = "volume_change_24h")
    val volumeChange24h: Double,
    @Json(name = "percent_change_1h")
    val percentChange1h: Double,
    @Json(name = "percent_change_24h")
    val percentChange24h: Double,
    @Json(name = "percent_change_7d")
    val percentChange7d: Double,
    @Json(name = "market_cap")
    val marketCap: Double,
    @Json(name = "market_cap_dominance")
    val marketCapDominance: Double,
    @Json(name = "fully_diluted_market_cap")
    val fullyDilutedMarketCap: Double,
    @Json(name = "last_updated")
    val lastUpdated: String,
)
