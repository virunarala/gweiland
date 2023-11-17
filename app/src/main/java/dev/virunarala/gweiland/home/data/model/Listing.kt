package dev.virunarala.gweiland.home.data.model

import com.squareup.moshi.Json

data class Listing(
    val id: Int,

    val name: String,

    val symbol: String,

    val slug: String,

    @Json(name = "cmc_rank")
    val cmcRank: Int?,

    @Json(name = "num_market_pairs")
    val numMarketPairs: Int?,

    @Json(name = "circulating_supply")
    val circulatingSupply: Double?,

    @Json(name = "total_supply")
    val totalSupply: Double?,

    @Json(name = "max_supply")
    val maxSupply: Double?,

    @Json(name = "infinite_supply")
    val infiniteSupply: Boolean?,

    @Json(name = "last_updated")
    val lastUpdated: String?,

    @Json(name = "date_added")
    val dateAdded: String?,

    val tags: List<String>?,

    val platform: Platform?,

    @Json(name = "self_reported_circulating_supply")
    val selfReportedCirculatingSupply: Double?,

    @Json(name = "self_reported_market_cap")
    val selfReportedMarketCap: Double?,

    val quote: Quote,
) {

    fun getRoundedUsdPrice(): Double {
        return String.format("%.2f",quote.usd.price).toDouble()
    }

    fun getDayPercentChange(): Double {
        return String.format("%.2f",quote.usd.percentChange24h).toDouble()
    }
}