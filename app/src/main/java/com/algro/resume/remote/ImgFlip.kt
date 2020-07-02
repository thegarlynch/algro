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
    suspend fun captionImage() : Envelope<Nothing?>

}

@JsonClass(generateAdapter = true)
data class MemeResponse(val memes : List<Meme>)

@JsonClass(generateAdapter = true)
data class CaptionResponse(
    @Json(name = "url") val imageUrl : String,
    @Json(name = "page_url") val pageUrl : String
)