package org.chelak.ea.screens.estate.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.R
import org.chelak.ea.common.Logger
import org.chelak.ea.ui.MainActivity
import org.chelak.ea.ui.dialog.debugAlert
import org.chelak.ea.ui.dialog.presentAlert
import org.chelak.ea.ui.dialog.presentTextInput
import org.chelak.ea.ui.estateId
import org.chelak.ea.ui.list.setVerticalLayout

class EstateDetailsFragment : Fragment() {

    private val adapter = EstateDetailsAdapter()

    private val viewModel: EstateDetailsViewModel by lazy {
        ViewModelProvider(this).get(EstateDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.estate_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.let {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.setVerticalLayout()
            recyclerView.adapter = adapter
        }
        (activity as? MainActivity)?.component?.inject(viewModel)
        arguments?.estateId?.let {
            viewModel.setEstateId(it)
        }
        viewModel.meters.observe(viewLifecycleOwner, Observer {
            Logger.d("Meters: $it")
            adapter.setMeters(it) { uid ->
                viewModel.openMeter(uid)
            }
        })
        viewModel.estate.observe(viewLifecycleOwner, Observer {
            val title = it.title ?: ""
            adapter.setEstate(title) {
                debugAlert()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.estate_details, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.estate_details_menu_add_meter -> {
                presentTextInput(
                    title = getString(R.string.meter_dialog_new),
                    positiveTitle = getString(R.string.btn_ok),
                    positiveAction = { name -> viewModel.addMeter(name) },
                    negativeTitle = getString(R.string.btn_cancel)
                )
                true
            }
            R.id.estate_details_menu_manage_rates -> {
                viewModel.manageRates()
                true
            }
            R.id.estate_details_menu_payment_settings -> {
                viewModel.paymentSettings()
                true
            }
            R.id.estate_details_menu_delete -> {
                presentAlert(
                    title = getString(R.string.dialog_title_warning),
                    message = getString(R.string.dialog_confirm_undone_action),
                    positiveTitle = getString(R.string.btn_delete),
                    positiveAction = { viewModel.deleteEstate() },
                    negativeTitle = getString(R.string.btn_cancel)
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
