package org.chelak.ea.screens.estate.calculation.editor.step

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.screens.estate.calculation.editor.SelectionListItem
import org.chelak.ea.ui.list.ArrayListAdapter
import org.chelak.ea.ui.list.CheckboxViewHolder

class StepSelectAdapter : ArrayListAdapter<SelectionListItem, RecyclerView.ViewHolder>() {

    var isMultipleChoice: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CheckboxViewHolder.instance(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? CheckboxViewHolder)?.let { vh ->
            val item = get(position)
            vh.setTitle(item.title)
            vh.setChecked(item.isSelected)
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