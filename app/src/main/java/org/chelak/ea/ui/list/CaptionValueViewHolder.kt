package org.chelak.ea.ui.list

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.R

class CaptionValueViewHolder(view: View): RecyclerView.ViewHolder(view) {

    companion object {
        fun instance(parent: ViewGroup): CaptionValueViewHolder =
            CaptionValueViewHolder(inflateView(parent, R.layout.viewholder_title_value))
    }

    private val captionText: TextView = view.findViewById(R.id.cell_title)
    private val valueText: TextView = view.findViewById(R.id.cell_value)

    fun setCaption(caption: String?) {
        captionText.text = caption
    }

    fun setCaption(resourceId: Int) {
        captionText.text = getContext().getString(resourceId)
    }

    fun setValue(caption: String?) {
        valueText.text = caption
    }

    fun setValue(resourceId: Int) {
        valueText.text = getContext().getString(resourceId)
    }

}