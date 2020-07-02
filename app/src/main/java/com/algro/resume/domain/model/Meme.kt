package com.algro.resume.domain.model

import com.squareup.moshi.JsonClass

/**
 *  Note : This is also used as Model for Remote
 */
@JsonClass(generateAdapter = true)
data class Meme(val id : String, val name : String, val url : String, val width : Int, val height : String)