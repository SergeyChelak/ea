package org.chelak.ea.screens.estate.rates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.database.entity.Rate
import org.chelak.ea.ui.list.CaptionValueViewHolder

class RateListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var rates: List<Rate> = listOf()

    fun updateRates(rates: List<Rate>) {
        this.rates = rates
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CaptionValueViewHolder.instance(parent)

    override fun getItemCount(): Int = rates.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? CaptionValueViewHolder)?.let {
            val item = rates[position]
            it.setCaption(item.title)
            //it.setValue(item.value)
        }
    }

}