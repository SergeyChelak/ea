package org.chelak.ea.screens.estate.calculation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.chelak.ea.R
import org.chelak.ea.ui.MainActivity
import org.chelak.ea.ui.estateId

class CalculationListFragment : Fragment() {

    private val viewModel: CalculationListViewModel by lazy {
        ViewModelProvider(this).get(CalculationListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.calculation_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.component?.inject(viewModel)
        arguments?.estateId?.let {
            viewModel.setEstateId(it)
        }
    }

}