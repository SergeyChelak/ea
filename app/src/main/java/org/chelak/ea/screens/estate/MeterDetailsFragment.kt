package org.chelak.ea.screens.estate

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import org.chelak.ea.R
import org.chelak.ea.ui.MainActivity
import org.chelak.ea.ui.meterId

class MeterDetailsFragment : Fragment() {

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
        (activity as? MainActivity)?.component?.inject(viewModel)
        assert(arguments?.meterId != null) { Log.e("MeterDetailsFragment", "meterId is null") }
        arguments?.meterId?.let {
            viewModel.setMeterId(it)
        }
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