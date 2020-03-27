package org.chelak.ea.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.estate_list_fragment.*

import org.chelak.ea.R
import org.chelak.ea.common.Logger
import org.chelak.ea.database.entity.Estate
import org.chelak.ea.ui.MainActivity
import org.chelak.ea.ui.appComponent
import org.chelak.ea.ui.list.ArrayListAdapter
import org.chelak.ea.ui.list.HeadingViewHolder
import org.chelak.ea.ui.list.clickPosition
import org.chelak.ea.ui.list.setVerticalLayout

class EstateListFragment : Fragment() {

    private lateinit var viewModel: EstateListViewModel

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
            recyclerView.clickPosition().observe({ lifecycle }) { position ->
                val estate = adapter[position]
                viewModel.openEstateDetails(estate.uid)
            }
            recyclerView.adapter = adapter
            recyclerView.setVerticalLayout()
            appendButton.setOnClickListener {
                viewModel.addEstate()
            }
        }
        viewModel = ViewModelProvider(this).get(EstateListViewModel::class.java)
        appComponent?.inject(viewModel)
        viewModel.getEstates().observe({ lifecycle }) { list ->
            Logger.i("Loaded ${list.size} estates")
            adapter.replace(list)
        }
    }

}
