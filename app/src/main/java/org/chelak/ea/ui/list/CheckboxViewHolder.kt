package org.chelak.ea.ui.list

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.R

class CheckboxViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun instance(parent: ViewGroup): CheckboxViewHolder =
            CheckboxViewHolder(inflateView(parent, R.layout.viewholder_checker))
    }

    private val titleText: TextView = view.findViewById(R.id.item_title)
    private val checkImage: ImageView = {
        val imageView: ImageView = view.findViewById(R.id.item_icon)
        imageView.setImageResource(R.drawable.ic_checked)
        imageView
    }()

    public fun setTitle(text: String?) {
        titleText.text = text
    }

    public fun setChecked(isChecked: Boolean) {
        checkImage.alpha = if (isChecked) 1.0f else 0.0f
    }

}