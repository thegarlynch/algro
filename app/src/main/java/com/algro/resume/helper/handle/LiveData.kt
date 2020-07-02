@file:Suppress("UNCHECKED_CAST")
package com.algro.resume.helper.handle

import androidx.lifecycle.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class SavedInstanceStateLiveDataDelegate<T>(
    private val handle: SavedStateHandle,
    private val initial: T,
    private val key : String?,
    private val distinctOnly : Boolean
) : ReadOnlyProperty<Any?, MutableLiveData<T>> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): MutableLiveData<T> {
        val instanceKey = key ?: property.name
        var liveData = handle.getLiveData<Any?>(instanceKey, initial)
        if(distinctOnly){
            liveData = LiveDataDistinctWrapper(
                liveData
            )
        }

        val returnedValue : MutableLiveData<T>
        if(initial is Set<*>){
            returnedValue = (liveData as MutableLiveData<Array<*>>).map { it.toSet() } as MutableLiveData<T>
        }else{
            returnedValue = liveData as MutableLiveData<T>
        }

        return returnedValue
    }
}

class LiveDataDistinctWrapper<T>(private val liveData: MutableLiveData<T>) : MediatorLiveData<T>(){

    init {
        addSource(liveData.distinctUntilChanged()){
            super.setValue(it)
        }
    }

    override fun getValue(): T? {
        return liveData.value
    }

    override fun postValue(value: T) {
        liveData.postValue(value)
    }

    override fun setValue(value: T) {
        liveData.value = value
    }

}


/**
 *
 * @return savedInstanceState as live data
 *
 */
fun <T> SavedStateHandle.liveData(initial: T, key : String? = null, distinctOnly: Boolean = true) : SavedInstanceStateLiveDataDelegate<T> {
    return SavedInstanceStateLiveDataDelegate(
        this,
        initial,
        key,
        distinctOnly
    )
}