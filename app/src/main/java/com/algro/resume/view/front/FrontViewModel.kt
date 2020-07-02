package com.algro.resume.view.front

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.algro.resume.core.AlgroDatabase
import com.algro.resume.domain.usecase.FetchMemesUseCase
import kotlinx.coroutines.Dispatchers

class FrontViewModel @ViewModelInject constructor(
    private val fetchMemes: FetchMemesUseCase,
    db: AlgroDatabase
) : ViewModel() {

    val memes = liveData(Dispatchers.IO) {
        this.emitSource(db.memeDao().getAllMemes())
        fetchMemes.fetch()
    }

    suspend fun refreshMemes() = fetchMemes.fetch()

}