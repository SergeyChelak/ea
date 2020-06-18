package org.chelak.ea.screens.estate.details

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.R
import org.chelak.ea.ui.VoidHandler
import org.chelak.ea.ui.list.getContext
import org.chelak.ea.ui.list.inflateView

class EstateViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun instance(parent: ViewGroup): EstateViewHolder =
            EstateViewHolder(inflateView(parent, R.layout.viewholder_estate_heading))
    }

    private val imageView: ImageView = view.findViewById(R.id.item_icon)
    private val textView: TextView = view.findViewById(R.id.item_title)
    private val buttonCalculate: Button = view.findViewById(R.id.buttonCalculate)

    fun setImageId(imageId: Int) {
        imageView.setImageResource(imageId)
    }

    fun setTitle(title: String?) {
        textView.text = title
    }

    fun setTitle(resourceId: Int) {
        textView.text = getContext().getString(resourceId)
    }

    fun setButtonHandler(handler: VoidHandler?) {
        buttonCalculate.setOnClickListener {
            handler?.invoke()
        }
    }

}


open class PaymentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val titleView: TextView = view.findViewById(R.id.titleLabel)
    private val valueTextView: TextView = view.findViewById(R.id.lastPaymentPrice)
    private val deltaTextView: TextView = view.findViewById(R.id.lastPaymentDelta)
    private val trendingImage: ImageView = view.findViewById(R.id.imageTrending)

    init {
        view.setOnClickListener {
            cellClickHandler?.invoke()
        }
    }

    var cellClickHandler: VoidHandler? = null

    fun setValue(price: String) {
        this.valueTextView.text = price
    }

    fun setDelta(delta: String) {
        this.deltaTextView.text = delta
    }

    fun setTrendDirection(trendDirection: TrendDirection) {
        when (trendDirection) {
            TrendDirection.DOWN -> trendingImage.setImageResource(R.drawable.ic_trend_down)
            TrendDirection.UP -> trendingImage.setImageResource(R.drawable.ic_trend_up)
            else -> trendingImage.setImageDrawable(null)
        }
    }

    fun setTitle(text: String) {
        this.titleView.text = text
    }

}


class LastPaymentViewHolder(view: View) : PaymentViewHolder(view) {

    companion object {
        fun instance(parent: ViewGroup): LastPaymentViewHolder =
            LastPaymentViewHolder(inflateView(parent, R.layout.viewholder_estate_payment))
    }

    private var buttonHandler: VoidHandler? = null

    private val buttonPayments: Button = view.findViewById(R.id.buttonPayments)

    fun setButtonHandler(handler: VoidHandler?) {
        this.buttonHandler = handler
        buttonPayments.setOnClickListener {
            buttonHandler?.invoke()
        }
    }

}


class MeterViewHolder(view: View) : PaymentViewHolder(view) {

    companion object {
        fun instance(parent: ViewGroup): MeterViewHolder =
            MeterViewHolder(inflateView(parent, R.layout.viewholder_estate_meter))
    }

}