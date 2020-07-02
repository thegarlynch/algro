package com.algro.resume.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Envelope<T>(
    val data : T?,
    @Json(name = "error_message") val errorMessage : String?
)