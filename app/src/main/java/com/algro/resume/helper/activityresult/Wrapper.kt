package com.algro.resume.helper.activityresult

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.github.florent37.inlineactivityresult.kotlin.InlineActivityResultException
import com.github.florent37.inlineactivityresult.kotlin.coroutines.startForResult as startActivityForResult


suspend fun Fragment.startForResult(intent : Intent, onFinished : (Intent) -> Unit){
    try {
        val result = startActivityForResult(intent)
        if(result.resultCode == Activity.RESULT_OK){
            onFinished.invoke(result.data!!)
        }
    }catch (ex : InlineActivityResultException){ }
}