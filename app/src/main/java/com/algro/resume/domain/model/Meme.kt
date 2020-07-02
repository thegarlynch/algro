package com.algro.resume.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.algro.resume.helper.recyclerview.WithId
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *  Note : This is also used as Model for Remote and Room Entity
 */
@Entity
@JsonClass(generateAdapter = true)
data class Meme(
    @PrimaryKey override val id : String,
    val name : String,
    @Json(name = "url")
    val imageUrl : String,
    val width : Int,
    val height : String
) : WithId<String>