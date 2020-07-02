package com.algro.resume.helper.livedata

import androidx.lifecycle.LiveData

fun <R> emit(block : () -> R) : LiveData<R> {
    val value = block.invoke()
    return androidx.lifecycle.liveData { emit(value) }
}