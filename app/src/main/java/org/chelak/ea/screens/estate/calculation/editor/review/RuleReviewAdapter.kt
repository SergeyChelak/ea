package org.chelak.ea.screens.estate.calculation.editor.review

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.ui.list.ArrayListAdapter
import org.chelak.ea.ui.list.CaptionValueViewHolder

class RuleReviewAdapter : ArrayListAdapter<RuleItem, RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CaptionValueViewHolder.instance(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? CaptionValueViewHolder)?.let {
            val item = get(position)
            it.setCaption(item.title)
            it.setValue(item.details)
        }
    }


}