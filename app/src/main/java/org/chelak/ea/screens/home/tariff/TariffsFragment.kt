package org.chelak.ea.screens.home.tariff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.estate_list_fragment.*

import org.chelak.ea.R
import org.chelak.ea.common.Logger
import org.chelak.ea.database.entity.Tariff
import org.chelak.ea.ui.appComponent
import org.chelak.ea.ui.dialog.alertBottomLabel
import org.chelak.ea.ui.dialog.alertTopLabel
import org.chelak.ea.ui.dialog.presentTwoTextFieldDialog
import org.chelak.ea.ui.list.ArrayListAdapter
import org.chelak.ea.ui.list.CaptionValueViewHolder
import org.chelak.ea.ui.list.clickPosition
import org.chelak.ea.ui.list.setVerticalLayout

class TariffsFragment : Fragment() {

    private lateinit var viewModel: TariffsViewModel

    private val adapter = object : ArrayListAdapter<Tariff, CaptionValueViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaptionValueViewHolder = CaptionValueViewHolder.instance(parent)

        override fun onBindViewHolder(holder: CaptionValueViewHolder, position: Int) {
            holder.setCaptionOnly(this[position].title)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tariff_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TariffsViewModel::class.java)
        appComponent?.inject(viewModel)

        view?.let {
            recyclerView.clickPosition().observe(viewLifecycleOwner, Observer { position ->
                viewModel.openTariffDetails(adapter[position].uid)
            })
            recyclerView.adapter = adapter
            recyclerView.setVerticalLayout()
            appendButton.setOnClickListener {
                val options = Bundle().apply {
                    this.alertTopLabel = getString(R.string.tariff_label_title)
                    this.alertBottomLabel = getString(R.string.tariff_label_base_price)
                }
                presentTwoTextFieldDialog(
                    title = getString(R.string.tariff_dialog_new),
                    positiveTitle = getString(R.string.btn_ok),
                    positiveAction = { title, price -> viewModel.addTariff(title, price)},
                    negativeTitle = getString(R.string.btn_cancel),
                    options = options
                )
            }
        }

        viewModel.getTariffs().observe(viewLifecycleOwner, Observer {
            Logger.d("Items ${it.size}")
            adapter.replace(it)
        })
    }

}
