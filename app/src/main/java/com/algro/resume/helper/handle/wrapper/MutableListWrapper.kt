package com.algro.resume.helper.handle.wrapper

/**
 *  Call notifier every times there is a change to list
 *
 */
internal class MutableListWrapper<T>(private val innerList: MutableList<T>, private val notifier: (List<T>) -> Unit) : MutableList<T>{

    private fun notifyChange(){
        notifier.invoke(innerList)
    }

    override fun add(element: T): Boolean {
        val result = innerList.add(element)
        notifyChange()
        return result
    }

    override fun add(index: Int, element: T) {
        innerList.add(index, element)
        notifyChange()
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        if(elements.isEmpty()) return true
        val result = innerList.addAll(index, elements)
        notifyChange()
        return result
    }

    override fun addAll(elements: Collection<T>): Boolean {
        if(elements.isEmpty()) return true
        val result = innerList.addAll(elements)
        notifyChange()
        return result
    }

    override fun clear() {
        innerList.clear()
        notifyChange()
    }

    override fun remove(element: T): Boolean {
        val result = innerList.remove(element)
        notifyChange()
        return result
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        if(elements.isEmpty()) return true
        val result = innerList.removeAll(elements)
        notifyChange()
        return result
    }

    override fun removeAt(index: Int): T {
        val result = innerList.removeAt(index)
        notifyChange()
        return result
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        val result = innerList.retainAll(elements)
        notifyChange()
        return result
    }

    override fun set(index: Int, element: T): T {
        val result = innerList.set(index, element)
        notifyChange()
        return result
    }

    // Delegate to InnerClass

    override val size: Int get() = innerList.size
    override fun contains(element: T) = innerList.contains(element)
    override fun containsAll(elements: Collection<T>) = innerList.containsAll(elements)
    override fun get(index: Int) = innerList.get(index)
    override fun indexOf(element: T) = innerList.indexOf(element)
    override fun isEmpty() = innerList.isEmpty()
    override fun iterator(): MutableIterator<T> = innerList.iterator()
    override fun lastIndexOf(element: T) = innerList.lastIndexOf(element)
    override fun listIterator() = innerList.listIterator()
    override fun listIterator(index: Int)= innerList.listIterator(index)
    override fun subList(fromIndex: Int, toIndex: Int) = innerList.subList(fromIndex, toIndex)

    override fun toString(): String {
        return innerList.toString()
    }
}