package com.algro.resume.helper.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/***
 *  Vertical Linear Layout Manager with Spacing for RecyclerView
 */
fun RecyclerView.vertical(spacing : Int = 0){
    layoutManager = LinearLayoutManager(context).apply { orientation = LinearLayoutManager.VERTICAL }
    addItemDecoration(VerticalDivider(spacing))
}