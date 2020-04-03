package org.chelak.ea.screens.home.tariff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.tariff_details_fragment.*
import kotlinx.android.synthetic.main.tariff_details_fragment.recyclerView

import org.chelak.ea.R
import org.chelak.ea.common.Logger
import org.chelak.ea.database.entity.TariffThreshold
import org.chelak.ea.ui.appComponent
import org.chelak.ea.ui.argumentContainer
import org.chelak.ea.ui.list.ArrayListAdapter
import org.chelak.ea.ui.list.CaptionValueViewHolder
import org.chelak.ea.ui.list.setVerticalLayout

class TariffDetailsFragment : Fragment() {

    private lateinit var viewModel: TariffDetailsViewModel

    private val adapter = object : ArrayListAdapter<TariffThreshold, CaptionValueViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaptionValueViewHolder = CaptionValueViewHolder.instance(parent)

        override fun onBindViewHolder(holder: CaptionValueViewHolder, position: Int) {
            val t = this[position]
            holder.setCaption((t.value ?: 0).toString())
            holder.setValue(t.price.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tariff_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TariffDetailsViewModel::class.java)
        appComponent?.inject(viewModel)
        viewModel.setTariffId(argumentContainer.tariffId)

        viewModel.tariff.observe(viewLifecycleOwner, Observer {
            tariffTitleLabel.text = it.title
        })
        viewModel.thresholds.observe(viewLifecycleOwner, Observer {
            Logger.d("Threshold count: ${it.size}")
            adapter.replace(it)
        })
        recyclerView.adapter = adapter
        recyclerView.setVerticalLayout()
        appendButton.setOnClickListener { _ ->
            //
        }
    }

}
