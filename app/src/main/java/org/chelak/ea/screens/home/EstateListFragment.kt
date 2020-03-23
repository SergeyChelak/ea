package org.chelak.ea.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.estate_list_fragment.*

import org.chelak.ea.R
import org.chelak.ea.common.Logger
import org.chelak.ea.database.entity.Estate
import org.chelak.ea.ui.MainActivity
import org.chelak.ea.ui.list.ArrayListAdapter

class EstateListFragment : Fragment() {

    private lateinit var viewModel: EstateListViewModel

    private val adapter = object : ArrayListAdapter<Estate, RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            TODO("Not yet implemented")
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
            test_btn_open_estate.setOnClickListener {
                viewModel.openEstateDetails(0)
            }
            dbg_btn_propagateEstate.setOnClickListener {
                viewModel.addEstate()
            }
        }
        viewModel = ViewModelProvider(this).get(EstateListViewModel::class.java)
        (activity as? MainActivity)?.component?.inject(viewModel)
        viewModel.getEstates().observe({ lifecycle }) { list ->
            Logger.i("Loaded ${list.size} estates")
            adapter.replace(list)
        }
    }

}
