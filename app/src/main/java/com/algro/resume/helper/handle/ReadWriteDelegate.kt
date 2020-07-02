package com.algro.resume.helper.handle

import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SavedInstanceStateDelegate<T>(
    private val handle: SavedStateHandle,
    private val initial: T,
    private val key : String?
) : ReadWriteProperty<Any?, T> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val instanceKey = key ?: property.name

        if(!handle.contains(instanceKey)) {
            handle.set(instanceKey, initial)
        }

        val value = handle.get<Any?>(instanceKey)

        @Suppress("UNCHECKED_CAST")
        return value as T ?: initial
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        val instanceKey = key ?: property.name
        handle.set(instanceKey, value)
    }

}

/**
 * if you changed the property that is delegated by this, it would update
 * savedInstanceState automatically
 *
 * @return savedInstanceState as it's value
 */
fun <T> SavedStateHandle.delegate(initial: T, key: String? = null) : SavedInstanceStateDelegate<T> {
    return SavedInstanceStateDelegate(
        this,
        initial,
        key
    )
}
