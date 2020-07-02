package com.algro.resume.domain.usecase

import com.algro.resume.core.AlgroDatabase
import com.algro.resume.remote.ImgFlip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchMemesUseCase @Inject constructor(
    db : AlgroDatabase,
    private val remote : ImgFlip
){

    private val dao = db.memeDao()

    suspend fun fetch() = withContext(Dispatchers.IO){
        val resp = remote.getMemes()
        dao.insertAll(resp.data!!.memes)
    }

}