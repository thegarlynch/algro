package com.algro.resume.helper.handle.wrapper

/**
 *  Call notifier every times there is a change to set
 **/
internal class MutableMapWrapper<K,V>(private val innerMap : MutableMap<K,V>, private val notifier : (Map<K, V>) -> Unit) : MutableMap<K,V> {

    private fun notifyChange(){
        notifier.invoke(innerMap)
    }

    override val size: Int get() = innerMap.size

    override fun containsKey(key: K): Boolean = innerMap.containsKey(key)

    override fun containsValue(value: V): Boolean = innerMap.containsValue(value)

    override fun get(key: K): V? = innerMap[key]

    override fun isEmpty(): Boolean = innerMap.isEmpty()

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>> get() = innerMap.entries
    override val keys: MutableSet<K> get() = innerMap.keys
    override val values: MutableCollection<V> get() = innerMap.values

    override fun clear() {
        innerMap.clear()
        notifyChange()
    }

    override fun put(key: K, value: V): V? {
        val res = innerMap.put(key, value)
        notifyChange()
        return res
    }

    override fun putAll(from: Map<out K, V>) {
        innerMap.putAll(from)
        notifyChange()
    }

    override fun remove(key: K): V? {
        val res = innerMap.remove(key)
        notifyChange()
        return res
    }


}