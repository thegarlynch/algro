package com.algro.resume.activity

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


/***
 *
 *  Is used all over application to enable keyboard dismissal
 *  Don't forget to also used
 *  @see com.algro.resume.helper.view.FocusedScrollView
 *
 *  Also contains logic for auto disposal of Rx Disposable [autoDispose]
 */

abstract class AbstractActivity : AppCompatActivity {

    private val destroyedDisposable = CompositeDisposable()
    private val stopDisposable = CompositeDisposable()
    private val pauseDisposable = CompositeDisposable()

    constructor() : super()
    constructor(@LayoutRes layoutId : Int) : super(layoutId)

    @CallSuper
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        var mRootView = findViewById<View>(android.R.id.content)
        if(mRootView == null){
            mRootView = window.decorView
        }
        mRootView.isClickable = true
        mRootView.isFocusableInTouchMode = true
    }

    fun Disposable.autoDispose(mode : DisposeOn = DisposeOn.STOP){
        when(mode){
            DisposeOn.DESTROYED -> destroyedDisposable.add(this)
            DisposeOn.PAUSE -> pauseDisposable.add(this)
            DisposeOn.STOP -> stopDisposable.add(this)
        }
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        pauseDisposable.clear()
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        stopDisposable.clear()
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        destroyedDisposable.clear()
    }
}
