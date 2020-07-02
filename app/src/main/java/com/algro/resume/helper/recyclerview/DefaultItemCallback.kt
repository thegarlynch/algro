package com.algro.resume.helper.recyclerview

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

interface WithId<ID> {
    val id : ID
}

class DefaultItemCallback<T : WithId<*>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

}