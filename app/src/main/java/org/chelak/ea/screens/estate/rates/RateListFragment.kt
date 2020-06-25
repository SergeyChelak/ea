package org.chelak.ea.screens.estate.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rate_list_fragment.view.*
import org.chelak.ea.R
import org.chelak.ea.ui.MainActivity
import org.chelak.ea.ui.dialog.*
import org.chelak.ea.ui.estateId
import org.chelak.ea.ui.list.clickPosition
import org.chelak.ea.ui.list.setVerticalLayout

class RateListFragment : Fragment() {
    val adapter = RateListAdapter()

    private val viewModel: RateListViewModel by lazy {
        ViewModelProvider(this).get(RateListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rate_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.component?.inject(viewModel)
        arguments?.estateId?.let {
            viewModel.setEstateId(it)
        }
        viewModel.rates.observe(viewLifecycleOwner, Observer {
            adapter.updateRates(it)
        })
        view?.let {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.setVerticalLayout()
            recyclerView.adapter = adapter
            recyclerView.clickPosition().observe(viewLifecycleOwner, Observer {
                val item = adapter.itemAt(it)
                changeRateItem(item)
            })
            it.appendButton.setOnClickListener {
                changeRateItem(RateModel())
            }
        }
    }

    private fun changeRateItem(rate: RateModel) {
        val options = Bundle().apply {
            this.alertTopLabel = getString(R.string.rate_dialog_label_title)
            this.alertBottomLabel = getString(R.string.rate_dialog_label_value)
            this.alertInitialTextValue = rate.title
            this.alertInitialSecondTextValue = rate.value
        }
        var dialogTitle = getString(R.string.rate_dialog_add_rate)
        var neutralTitle: String? = null
        var neutralAction: DialogAction? = null

        if (rate.uid != 0L) {
            dialogTitle =  getString(R.string.rate_dialog_edit_rate)
            neutralTitle = getString(R.string.btn_delete)
            neutralAction = {
                viewModel.delete(rate.uid)
            }
        }

        presentTwoTextFieldDialog(
            title = dialogTitle,
            positiveTitle = getString(R.string.btn_ok),
            positiveAction = { title, value ->
                viewModel.update(rate.uid, title, value)
            },
            negativeTitle = getString(R.string.btn_cancel),
            neutralTitle = neutralTitle,
            neutralAction = neutralAction,
            options = options
        )
    }

}