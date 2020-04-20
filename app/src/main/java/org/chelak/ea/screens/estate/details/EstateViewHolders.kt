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


class LastPaymentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun instance(parent: ViewGroup): LastPaymentViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.viewholder_estate_payment, parent, false)
            return LastPaymentViewHolder(view)
        }
    }

    private val buttonPayments: Button = view.findViewById(R.id.buttonPayments)
    private val message: TextView = view.findViewById(R.id.lastPaymentLabel)
    private val price: TextView = view.findViewById(R.id.lastPaymentPrice)
    private val delta: TextView = view.findViewById(R.id.lastPaymentDelta)
    private val trendingImage: ImageView = view.findViewById(R.id.imageTrending)

    private var buttonHandler: ButtonHandler? = null

    fun setPaymentPrice(price: String) {
        this.price.text = price
    }

    fun setDelta(delta: String) {
        this.delta.text = delta
    }

    fun setTrend(isUpTrend: Boolean) {
        trendingImage.setImageResource(if (isUpTrend) R.drawable.ic_trend_up else R.drawable.ic_trend_down)
    }

    fun setPaymentMessage(message: String) {
        this.message.text = message
    }

    fun setButtonHandler(handler: ButtonHandler?) {
        this.buttonHandler = handler
        buttonPayments.setOnClickListener {
            buttonHandler?.invoke()
        }
    }
}