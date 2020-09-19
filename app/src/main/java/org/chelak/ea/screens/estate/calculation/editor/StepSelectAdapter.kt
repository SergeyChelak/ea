package org.chelak.ea.screens.estate.calculation.editor

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.R
import org.chelak.ea.ui.list.ArrayListAdapter
import org.chelak.ea.ui.list.CaptionValueViewHolder

class StepSelectAdapter : ArrayListAdapter<SelectionListItem, RecyclerView.ViewHolder>() {

    var isMultipleChoice: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CaptionValueViewHolder.instance(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? CaptionValueViewHolder)?.let { vh ->
            val item = get(position)
            vh.setCaptionOnly(item.title)
            val colorId = if (item.isSelected) R.color.colorListItemSelected else R.color.colorListItemDefault
            vh.itemView.setBackgroundResource(colorId)
        }
    }

    fun onItemClick(position: Int) {
        if (!isMultipleChoice) {
            items.forEach { it.isSelected = false }
        }
        val item = get(position)
        item.isSelected = !item.isSelected
        notifyDataSetChanged()
    }

}