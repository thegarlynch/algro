package com.algro.resume.helper.viewmodel

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController


/**
 *  This extension is best used when using
 *  [dagger.hilt.android.AndroidEntryPoint] +
 *  [androidx.hilt.lifecycle.ViewModelInject] +
 *  [androidx.hilt.Assisted]
 *  as it will resolves SavedStateHandle automatically
 *
 *  @sample
 *
 *  class ExampleViewModel @ViewModelInject constructor(
 *      @Assisted handle : SavedStateHandle
 *  )
 *
 *  @AndroidEntryPoint
 *  class ExampleFragment : Fragment {
 *
 *     val viewModel : ExampleViewModel by viewModel()
 *
 *  }
 *
 *
 */

inline fun <reified VM : ViewModel> FragmentActivity.viewModel() : Lazy<VM>{
    return lazy { getViewModel<VM>() }
}

inline fun <reified VM : ViewModel> Fragment.viewModel() : Lazy<VM>{
    return lazy { ViewModelProvider(this).get(VM::class.java) }
}

inline fun <reified VM : ViewModel> Fragment.activityViewModel() : Lazy<VM> {
    return lazy { getActivityViewModel<VM>() }
}

inline fun <reified VM : ViewModel> Fragment.backStackViewModel(@IdRes id : Int) : Lazy<VM>{
    return lazy { getBackStackViewModel<VM>(id) }
}

inline fun <reified VM : ViewModel> Fragment.navGraphViewModel(@IdRes id : Int) : Lazy<VM> {
    return lazy { getNavGraphViewModel<VM>(id) }
}

inline fun <reified VM : ViewModel> Fragment.getViewModel() : VM {
    return ViewModelProvider(this).get(VM::class.java)
}
inline fun <reified VM : ViewModel> FragmentActivity.getViewModel(): VM {
    return ViewModelProvider(this).get(VM::class.java)
}
inline fun <reified VM : ViewModel> Fragment.getActivityViewModel() : VM {
    return ViewModelProvider(requireActivity()).get(VM::class.java)
}
inline fun <reified VM : ViewModel> Fragment.getBackStackViewModel(@IdRes id : Int) : VM{
    return ViewModelProvider(findNavController().getBackStackEntry(id)).get(VM::class.java)
}
inline fun <reified VM : ViewModel> Fragment.getNavGraphViewModel(@IdRes id : Int): VM {
    return ViewModelProvider(findNavController().getViewModelStoreOwner(id)).get(VM::class.java)
}