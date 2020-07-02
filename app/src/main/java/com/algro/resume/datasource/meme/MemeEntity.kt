package com.algro.resume.datasource.meme

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MemeEntity(
    @PrimaryKey val id : String,
    val name : String,
    val url : String,
    val width : Int,
    val height : Int
)