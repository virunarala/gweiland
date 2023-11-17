package dev.virunarala.gweiland.home.data.model

import com.squareup.moshi.Json

data class Platform(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "symbol")
    val symbol: String,

    @Json(name = "slug")
    val slug: String,

    @Json(name = "token_address")
    val tokenAddress: String
)
