package org.chelak.ea.screens.estate.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.R
import org.chelak.ea.ui.list.getContext

typealias ButtonHandler = () -> Unit

class EstateViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun instance(parent: ViewGroup): EstateViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.viewholder_estate_heading, parent, false)
            return EstateViewHolder(view)
        }
    }

    private val imageView: ImageView = view.findViewById(R.id.item_icon)
    private val textView: TextView = view.findViewById(R.id.item_title)
    private val buttonCalculate: Button = view.findViewById(R.id.buttonCalculate)

    private var buttonHandler: ButtonHandler? = null

    fun setImageId(imageId: Int) {
        imageView.setImageResource(imageId)
    }

    fun setTitle(title: String?) {
        textView.text = title
    }

    fun setTitle(resourceId: Int) {
        textView.text = getContext().getString(resourceId)
    }

    fun setButtonHandler(handler: ButtonHandler?) {
        this.buttonHandler = handler
        buttonCalculate.setOnClickListener {
            buttonHandler?.invoke()
        }
    }

}