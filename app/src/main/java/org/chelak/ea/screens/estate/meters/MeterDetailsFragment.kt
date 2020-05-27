package org.chelak.ea.screens.estate.meters

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

import org.chelak.ea.R
import org.chelak.ea.common.Logger
import org.chelak.ea.database.entity.MeterValue
import org.chelak.ea.ui.MainActivity
import org.chelak.ea.ui.list.setVerticalLayout
import org.chelak.ea.ui.meterId

class MeterDetailsFragment : Fragment() {

    private val adapter = MeterDetailsAdapter()

    private val viewModel: MeterDetailsViewModel by lazy {
        ViewModelProvider(this).get(MeterDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.meter_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.let {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.setVerticalLayout()
            recyclerView.adapter = adapter
        }
        (activity as? MainActivity)?.component?.inject(viewModel)
        assert(arguments?.meterId != null) { Logger.e("MeterDetailsFragment: arguments.meterId is null") }
        arguments?.meterId?.let {
            viewModel.setMeterId(it)
        }
        viewModel.meterValues.observe(viewLifecycleOwner, Observer {
            adapter.setValues(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.estate_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.meter_details_menu_edit_properties -> {
                true
            }
            R.id.meter_details_menu_delete -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}