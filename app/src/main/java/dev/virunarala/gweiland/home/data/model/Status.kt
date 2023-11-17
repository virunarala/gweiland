package dev.virunarala.gweiland.home.data.model

import com.squareup.moshi.Json

data class Status(
    val timestamp: String,

    @Json(name = "error_code")
    val errorCode: Int,

    @Json(name = "error_message")
    val errorMessage: String?,

    val elapsed: Int,

    @Json(name = "credit_count")
    val creditCount: Int,

    @Json(name = "notice")
    val notice: String?
)
