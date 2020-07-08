package org.chelak.ea.screens.estate.rates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.ui.list.CaptionValueViewHolder

class RateListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var rates: List<RateData> = listOf()

    fun updateRates(rates: List<RateData>) {
        this.rates = rates
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CaptionValueViewHolder.instance(parent)

    override fun getItemCount(): Int = rates.size

    fun itemAt(position: Int): RateData = rates[position]

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? CaptionValueViewHolder)?.let {
            val item = rates[position]
            it.setCaption(item.title)
            it.setValue(item.value)
        }
    }

}