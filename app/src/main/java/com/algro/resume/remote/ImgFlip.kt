package com.algro.resume.remote

import com.algro.resume.domain.model.Meme
import com.squareup.moshi.JsonClass
import retrofit2.http.GET

interface ImgFlip {

    @GET("get_memes")
    suspend fun getMemes() : Envelope<MemeResponse>

}

@JsonClass(generateAdapter = true)
data class MemeResponse(val memes : List<Meme>)