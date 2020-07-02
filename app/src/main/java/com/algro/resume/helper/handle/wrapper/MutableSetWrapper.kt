package com.algro.resume.helper.handle.wrapper

/**
 *  Call notifier every times there is a change to set
 **/
internal class MutableSetWrapper<T>(private val innerSet : MutableSet<T>, private val notifier : (Set<T>) -> Unit) : MutableSet<T> {

    private fun notifyChange(){
        notifier.invoke(innerSet)
    }

    override fun add(element: T): Boolean {
        val result = innerSet.add(element)
        notifyChange()
        return result
    }

    override fun addAll(elements: Collection<T>): Boolean {
        if(elements.isEmpty()) return true
        val result = innerSet.addAll(elements)
        notifyChange()
        return result
    }

    override fun clear() {
        innerSet.clear()
    }

    override fun remove(element: T): Boolean {
        val result = innerSet.remove(element)
        notifyChange()
        return result
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        if(elements.isEmpty()) return true
        val result = innerSet.removeAll(elements)
        notifyChange()
        return result
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        val result = innerSet.retainAll(elements)
        notifyChange()
        return result
    }

    override fun iterator(): MutableIterator<T> = innerSet.iterator()
    override val size: Int get() = innerSet.size
    override fun contains(element: T): Boolean = innerSet.contains(element)
    override fun containsAll(elements: Collection<T>): Boolean = innerSet.containsAll(elements)
    override fun isEmpty(): Boolean = innerSet.isEmpty()

}