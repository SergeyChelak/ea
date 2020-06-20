package org.chelak.ea.screens.estate.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.R
import org.chelak.ea.ui.MainActivity
import org.chelak.ea.ui.estateId
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
        view?.let {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.setVerticalLayout()
            recyclerView.adapter = adapter
        }
        (activity as? MainActivity)?.component?.inject(viewModel)
        arguments?.estateId?.let {
            viewModel.setEstateId(it)
        }
        viewModel.rates.observe(viewLifecycleOwner, Observer {
            adapter.updateRates(it)
        })
    }

}