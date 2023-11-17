package dev.virunarala.gweiland.home.data.model

import com.squareup.moshi.Json

data class Urls(
    val website: List<String?>,
    @Json(name = "technical_doc")
    val technicalDoc: List<String?>,
    val twitter: List<String?>,
    val reddit: List<String?>,
    @Json(name = "message_board")
    val messageBoard: List<String?>,
    val announcement: List<String?>,
    val chat: List<String?>,
    val explorer: List<String?>,
    @Json(name = "source_code")
    val sourceCode: List<String>,
)