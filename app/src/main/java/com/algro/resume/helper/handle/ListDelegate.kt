package com.algro.resume.helper.handle

import androidx.lifecycle.SavedStateHandle
import com.algro.resume.helper.handle.wrapper.MutableListWrapper
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class ListSavedStateHandleDelegate<T>(
    private val handle: SavedStateHandle,
    private val initial: List<T>,
    private val key : String?
) : ReadOnlyProperty<Any?, MutableList<T>> {

    private lateinit var internalList : MutableList<T>
    private var instanceKey : String? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): MutableList<T> {
        instanceKey = key ?: property.name
        if(!handle.contains(instanceKey!!)) {
            handle.set(instanceKey!!, initial)
            internalList =
                MutableListWrapper(
                    initial.toMutableList(),
                    this::notifyChange
                )
        }
        return internalList
    }

    private fun notifyChange(value: List<T>){
        handle.set(instanceKey!!, value)
    }

}

fun <T> SavedStateHandle.delegate(initial: List<T>, key: String? = null) : ListSavedStateHandleDelegate<T> {
    return ListSavedStateHandleDelegate(
        this,
        initial,
        key
    )
}