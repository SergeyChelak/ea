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
import org.chelak.ea.ui.estateId
import org.chelak.ea.ui.list.setVerticalLayout

class EstateDetailsFragment : Fragment() {

    private val adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val typeTitle = 1
        private val typePayment = 2
        private val typeMeter = 3

        private var title: String? = null
        private var calculateHandler: ButtonHandler? = null

        override fun getItemViewType(position: Int): Int =
            when (position) {
                0 -> typeTitle
                1 -> typePayment
                else -> typeMeter
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//            when (viewType) {
//                typeTitle -> EstateViewHolder.instance(parent)
//                else -> null
//            }
            return EstateViewHolder.instance(parent)
        }

        override fun getItemCount(): Int {
            return 1
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when (getItemViewType(position)) {
                typeTitle -> (holder as? EstateViewHolder)?.let {
                    it.setTitle(title)
                    it.setButtonHandler(calculateHandler)
                    it.setImageId(R.drawable.ic_house)
                }
                else -> {
                    //
                }
            }
        }

        fun set(title: String, handler: ButtonHandler?) {
            this.title = title
            this.calculateHandler = handler
            notifyItemChanged(0)
        }
    }

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
        view?.let {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.setVerticalLayout()
            recyclerView.adapter = adapter
        }
        viewModel = ViewModelProvider(this).get(EstateDetailsViewModel::class.java)
        (activity as? MainActivity)?.component?.inject(viewModel)
        arguments?.estateId?.let {
            viewModel.setEstateId(it)
        }
        viewModel.meters.observe(viewLifecycleOwner, Observer {
            Logger.d("Meters: $it")
        })
        viewModel.estate.observe(viewLifecycleOwner, Observer {
            val title = it.title ?: ""
            adapter.set(title = title, handler = {
                debugAlert()
            })
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
