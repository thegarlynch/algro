package com.algro.resume.fragment

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 *  Also contains logic for auto disposal of Rx Disposable [autoDispose]
 */
abstract class AbstractFragment : Fragment {
    constructor() : super()
    constructor(@LayoutRes layoutId : Int) : super(layoutId)

    lateinit var navController: NavController
        private set

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    private val destroyedDisposable = CompositeDisposable()
    private val viewDestroyedDisposable = CompositeDisposable()
    private val detachedDisposable = CompositeDisposable()
    private val stopDisposable = CompositeDisposable()
    private val pauseDisposable = CompositeDisposable()

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        destroyedDisposable.clear()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        viewDestroyedDisposable.clear()
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        stopDisposable.clear()
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        pauseDisposable.clear()
    }

    @CallSuper
    override fun onDetach() {
        super.onDetach()
        detachedDisposable.clear()
    }

    fun Disposable.autoDispose(mode : DisposeOn = DisposeOn.STOP){
        when(mode){
            DisposeOn.DESTROYED -> destroyedDisposable.add(this)
            DisposeOn.STOP -> stopDisposable.add(this)
            DisposeOn.PAUSE -> pauseDisposable.add(this)
            DisposeOn.DETACHED -> detachedDisposable.add(this)
            DisposeOn.VIEW_DESTROYED -> viewDestroyedDisposable.add(this)
        }
    }

}