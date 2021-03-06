package org.chelak.ea.ui.list

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.R

class HeadingViewHolder(view: View): RecyclerView.ViewHolder(view) {

    companion object {
        fun instance(parent: ViewGroup): HeadingViewHolder =
            HeadingViewHolder(inflateView(parent, R.layout.viewholder_heading))
    }

    private val imageView: ImageView = view.findViewById(R.id.item_icon)
    private val textView: TextView = view.findViewById(R.id.item_title)

    fun setImageId(imageId: Int) {
        imageView.setImageResource(imageId)
    }

    fun setTitle(title: String?) {
        textView.text = title
    }

    fun setTitle(resourceId: Int) {
        textView.text = getContext().getString(resourceId)
    }

}