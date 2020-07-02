package com.algro.resume.helper.handle

import androidx.lifecycle.SavedStateHandle
import com.algro.resume.helper.handle.wrapper.MutableSetWrapper
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class SetSavedStateHandleDelegate<T>(
    private val handle: SavedStateHandle,
    private val initial: Set<T>,
    private val key : String?
) : ReadOnlyProperty<Any?, MutableSet<T>> {

    private lateinit var internalList : MutableSet<T>
    private var instanceKey : String? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): MutableSet<T> {
        instanceKey = key ?: property.name
        if(!handle.contains(instanceKey!!)) {
            handle.set(instanceKey!!, initial)
            internalList =
                MutableSetWrapper(
                    initial.toMutableSet(),
                    this::notifyChange
                )
        }
        return internalList
    }

    private fun notifyChange(value: Set<T>){
        handle.set(instanceKey!!, value)
    }

}


fun <T> SavedStateHandle.delegate(initial: Set<T>, key: String? = null) : SetSavedStateHandleDelegate<T> {
    return SetSavedStateHandleDelegate(
        this,
        initial,
        key
    )
}
