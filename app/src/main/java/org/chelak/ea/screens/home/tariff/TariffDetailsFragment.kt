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
import org.chelak.ea.ui.dialog.*
import org.chelak.ea.ui.list.ArrayListAdapter
import org.chelak.ea.ui.list.CaptionValueViewHolder
import org.chelak.ea.ui.list.clickPosition
import org.chelak.ea.ui.list.setVerticalLayout

class TariffDetailsFragment : Fragment() {

    private val viewModel: TariffDetailsViewModel by lazy {
        ViewModelProvider(this).get(TariffDetailsViewModel::class.java)
    }

    private val adapter = object : ArrayListAdapter<TariffThreshold, CaptionValueViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaptionValueViewHolder =
            CaptionValueViewHolder.instance(parent)

        override fun onBindViewHolder(holder: CaptionValueViewHolder, position: Int) {
            val t = this[position]
            val caption = if (t.value != null) t.value.toString() else "Base"
            holder.setCaption(caption)
            holder.setValue(t.price.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.tariff_details_fragment, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
        recyclerView.clickPosition().observe(viewLifecycleOwner, Observer {
            Logger.d("Threshold selected $it")
        })
        appendButton.setOnClickListener {
            val options = Bundle().apply {
                //this.alertInitialTextValue = "Initial text"
                this.alertTopLabel = "Starting from"
                this.alertBottomLabel = "Price"
            }
            presentTwoTextFieldDialog(
                "Tariff Threshold",
                positiveTitle = "OK",
                positiveAction = { top, bottom ->
                    Logger.d("Response: $top and $bottom")
                },
                negativeTitle = "Cancel",
                options = options
            )
        }
    }

}
