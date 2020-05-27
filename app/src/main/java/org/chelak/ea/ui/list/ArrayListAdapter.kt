package org.chelak.ea.ui.list

import androidx.recyclerview.widget.RecyclerView

abstract class ArrayListAdapter<T, VH: RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private var items: List<T> = emptyList()

    fun replace(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    operator fun get(position: Int): T = items[position]

    // TODO implement insert/delete/tale and head append
}