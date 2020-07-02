package com.algro.resume.view.front

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.algro.resume.core.AlgroDatabase
import com.algro.resume.domain.usecase.FetchMemesUseCase
import kotlinx.coroutines.Dispatchers

class FrontViewModel @ViewModelInject constructor(
    @Assisted handle: SavedStateHandle,
    private val fetchMemes: FetchMemesUseCase,
    db : AlgroDatabase
) : ViewModel() {

    val memes = liveData(Dispatchers.IO) {
        this.emitSource(db.memeDao().getAllMemes())
        fetchMemes.fetch()
    }

}