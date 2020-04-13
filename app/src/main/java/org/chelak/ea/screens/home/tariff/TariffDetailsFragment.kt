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
            val value = t.value ?: 0
            val caption = if (value > 0) {
                value.toString()
            } else {
                getString(R.string.tariff_label_base_price)
            }
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
            val threshold = adapter[it]
            openEditThresholdDialog(threshold)
        })
        appendButton.setOnClickListener {
            openEditThresholdDialog()
        }
    }

    private fun openEditThresholdDialog(threshold: TariffThreshold? = null) {
        val options = Bundle().apply {
            this.alertTopLabel = getString(R.string.threshold_label_start_from)
            this.alertBottomLabel = getString(R.string.threshold_label_price)
            threshold?.let {
                this.alertInitialTextValue = (threshold.value ?: 0).toString()
                this.alertInitialSecondTextValue = threshold.price.toString()
            }
        }
        val deleteAction: DialogAction?
        val deleteTitle: String?
        val positiveAction: TwoTextInputAction
        val title: String
        if (threshold != null) {
            title = getString(R.string.threshold_dialog_edit)
            deleteAction = { viewModel.deleteThreshold(threshold.uid) }
            deleteTitle = getString(R.string.btn_delete)
            positiveAction = { top, bottom ->
                viewModel.updateThreshold(id = threshold.uid, startFrom = top, price = bottom)
            }
        } else {
            title = getString(R.string.threshold_dialog_new)
            deleteAction = null
            deleteTitle = null
            positiveAction = { top, bottom ->
                viewModel.addThreshold(startFrom = top, price = bottom)
            }
        }
        presentTwoTextFieldDialog(
            title = title,
            positiveTitle = getString(R.string.btn_ok),
            positiveAction = positiveAction,
            negativeTitle = getString(R.string.btn_cancel),
            neutralTitle =  deleteTitle,
            neutralAction = deleteAction,
            options = options
        )
    }

}
