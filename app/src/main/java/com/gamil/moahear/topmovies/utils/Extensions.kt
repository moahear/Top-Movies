package com.gamil.moahear.topmovies.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun View.setVisible(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}

fun RecyclerView.initRecyclerView(
    adapter: RecyclerView.Adapter<*>,
    layoutManager: RecyclerView.LayoutManager
) {
    this.layoutManager = layoutManager
    this.adapter = adapter
}