package com.algro.resume.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.algro.resume.helper.recyclerview.WithId
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 *  Note : This is also used as Model for Remote and Room Entity
 */
@Entity
@JsonClass(generateAdapter = true)
@Parcelize
data class Meme(
    @PrimaryKey override val id : String,
    val name : String,
    @Json(name = "url")
    val imageUrl : String,
    val width : Int,
    val height : String
) : WithId<String>, Parcelable