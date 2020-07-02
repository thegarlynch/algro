package com.algro.resume.helper.handle

import androidx.lifecycle.SavedStateHandle
import com.algro.resume.helper.handle.wrapper.MutableMapWrapper
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class MapSavedStateHandleDelegate<K,V>(
    private val handle: SavedStateHandle,
    private val initial: Map<K, V>,
    private val key : String?
) : ReadOnlyProperty<Any?, MutableMap<K, V>> {

    private lateinit var internalList : MutableMap<K,V>
    private var instanceKey : String? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): MutableMap<K,V> {
        instanceKey = key ?: property.name
        if(!handle.contains(instanceKey!!)) {
            handle.set(instanceKey!!, initial)
            internalList =
                MutableMapWrapper(
                    initial.toMutableMap(),
                    this::notifyChange
                )
        }
        return internalList
    }

    private fun notifyChange(value: Map<K,V>){
        handle.set(instanceKey!!, value)
    }

}


fun <K,V> SavedStateHandle.delegate(initial: Map<K,V>, key: String? = null) : MapSavedStateHandleDelegate<K, V> {
    return MapSavedStateHandleDelegate(
        this,
        initial,
        key
    )
}
