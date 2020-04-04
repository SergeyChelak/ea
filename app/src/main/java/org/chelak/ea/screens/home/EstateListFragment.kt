package org.chelak.ea.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.estate_list_fragment.*
import org.chelak.ea.R
import org.chelak.ea.common.Logger
import org.chelak.ea.database.entity.Estate
import org.chelak.ea.ui.appComponent
import org.chelak.ea.ui.list.ArrayListAdapter
import org.chelak.ea.ui.list.HeadingViewHolder
import org.chelak.ea.ui.list.clickPosition
import org.chelak.ea.ui.list.setVerticalLayout

class EstateListFragment : Fragment() {

    private val viewModel: EstateListViewModel by lazy {
        ViewModelProvider(this).get(EstateListViewModel::class.java)
    }

    private val adapter = object : ArrayListAdapter<Estate, HeadingViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadingViewHolder =
            HeadingViewHolder.instance(parent)

        override fun onBindViewHolder(holder: HeadingViewHolder, position: Int) {
            val estate = this[position]
            holder.setTitle(estate.title)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.estate_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.let {
            recyclerView.clickPosition().observe(viewLifecycleOwner, Observer {
                val estate = adapter[it]
                viewModel.openEstateDetails(estate.uid)
            })
            recyclerView.adapter = adapter
            recyclerView.setVerticalLayout()
            appendButton.setOnClickListener {
                viewModel.addEstate()
            }
        }
        appComponent?.inject(viewModel)
        viewModel.getEstates().observe(viewLifecycleOwner, Observer { list ->
            Logger.i("Loaded ${list.size} estates")
            adapter.replace(list)
        })
    }

}
