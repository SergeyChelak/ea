package org.chelak.ea.screens.estate.meters

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import org.chelak.ea.R
import org.chelak.ea.ui.Keyboard
import org.chelak.ea.ui.MainActivity
import org.chelak.ea.ui.dialog.presentAlert
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
        adapter.saveHandler = { uid, userInput ->
            viewModel.saveValue(uid, userInput)
            context?.let { context ->
                Keyboard.hide(context)
            }
        }
        adapter.deleteHandler = { uid ->
            viewModel.deleteValue(uid)
            context?.let { context ->
                Keyboard.hide(context)
            }
        }
        view?.let {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView)
            val layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            adapter.scrollHandler = { pos ->
                layoutManager.scrollToPositionWithOffset(pos, 0)
            }
        }
        (activity as? MainActivity)?.component?.inject(viewModel)
        arguments?.meterId?.let {
            viewModel.setMeterId(it)
        }
        viewModel.meterValues.observe(viewLifecycleOwner, Observer {
            adapter.setValues(it)
        })
        viewModel.alertData.observe(viewLifecycleOwner, Observer {
            presentAlert(it)
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