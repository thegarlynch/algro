package com.algro.resume.remote

import com.algro.resume.domain.model.Meme
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.GET
import retrofit2.http.POST

interface ImgFlip {

    @GET("get_memes")
    suspend fun getMemes() : Envelope<MemeResponse>

    @POST("caption_image")
    suspend fun captionImage(request : CaptionRequest) : Envelope<CaptionResponse>

}

@JsonClass(generateAdapter = true)
data class MemeResponse(val memes : List<Meme>)

@JsonClass(generateAdapter = true)
data class CaptionRequest(
    @Json(name = "template_id") val templateId : String,
    val username : String,
    val password : String,
    /**
     *  Don't use [topText] and [bottomText]
     *  if [boxes] are used
     */
    @Json(name = "text0") val topText : String?,
    @Json(name = "text1") val bottomText : String?,
    /**
     *  Either impact or arial
     */
    val font : String? = null,
    /**
     *  ex : 50px
     */
    @Json(name = "max_font_size") val maxFontSize : String?,
    val boxes : List<Box>? = null
)

@JsonClass(generateAdapter = true)
data class Box(
    val text : String,
    val x : Int,
    val y : Int,
    val width : Int,
    val height : Int,
    /**
     *  ex : "#ffffff"
     */
    val color : String,
    /**
     *  ex : "#000000"
     */
    @Json(name = "outline_color") val outlineColor : String
)

@JsonClass(generateAdapter = true)
data class CaptionResponse(
    @Json(name = "url") val imageUrl : String,
    @Json(name = "page_url") val pageUrl : String
)
