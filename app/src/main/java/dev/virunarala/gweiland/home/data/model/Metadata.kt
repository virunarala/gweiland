package dev.virunarala.gweiland.home.data.model

import com.squareup.moshi.Json

data class Metadata(
    val urls: Urls?,
    val logo: String,
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    val description: String?,
    @Json(name = "date_added")
    val dateAdded: String?,
    @Json(name = "date_launched")
    val dateLaunched: String?,
    @Json(name = "notice")
    val notice: String?,
    val tags: List<String>?,
    val platform: Platform?,
    val category: String?,
)