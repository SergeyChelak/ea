package org.chelak.ea.screens.estate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.chelak.ea.R
import org.chelak.ea.ui.MainActivity

class EstateDetailsFragment : Fragment() {

    private lateinit var viewModel: EstateDetailsViewModel

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
    }

}
