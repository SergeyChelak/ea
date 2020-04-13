package org.chelak.ea.screens.estate.details

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.chelak.ea.R
import org.chelak.ea.common.Logger
import org.chelak.ea.ui.MainActivity
import org.chelak.ea.ui.dialog.presentAlert
import org.chelak.ea.ui.estateId

class EstateDetailsFragment : Fragment() {

    private lateinit var viewModel: EstateDetailsViewModel

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
        view.let {
            val button: Button? = it?.findViewById(R.id.dbg_button_meter_list)
            button?.setOnClickListener { _ ->
                viewModel.openMeterList()
            }
        }
        viewModel = ViewModelProvider(this).get(EstateDetailsViewModel::class.java)
        (activity as? MainActivity)?.component?.inject(viewModel)
        arguments?.estateId?.let {
            viewModel.setEstateId(it)
        }
        viewModel.meters.observe(viewLifecycleOwner, Observer {
            Logger.d("Meters: $it")
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.estate_details, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.estate_details_menu_add_meter -> {
                viewModel.addMeter()
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
                askDeleteEstate()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun askDeleteEstate() {
        presentAlert(
            title = getString(R.string.dialog_title_warning),
            message = getString(R.string.dialog_confirm_undone_action),
            positiveTitle = getString(R.string.btn_delete),
            positiveAction = { viewModel.deleteEstate() },
            negativeTitle = getString(R.string.btn_cancel)
            )
    }

}
