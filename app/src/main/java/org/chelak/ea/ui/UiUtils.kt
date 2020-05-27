package org.chelak.ea.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun inflateView(parent: ViewGroup, layoutId: Int): View {
    val inflater = LayoutInflater.from(parent.context)
    return inflater.inflate(layoutId, parent, false)
}