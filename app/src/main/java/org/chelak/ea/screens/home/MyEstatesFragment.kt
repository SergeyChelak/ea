package org.chelak.ea.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import kotlinx.android.synthetic.main.my_estates_fragment.*

import org.chelak.ea.R
import org.chelak.ea.common.Logger
import org.chelak.ea.ui.MainActivity

class MyEstatesFragment : Fragment() {

    private val viewModel: MyEstatesViewModel by navGraphViewModels(R.id.root_nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_estates_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.let {
            test_btn_open_estate.setOnClickListener {
                viewModel.openEstateDetails(0)
            }
        }
//        viewModel = ViewModelProvider(this).get(MyEstatesViewModel::class.java)
        (activity as? MainActivity)?.component?.inject(viewModel)
        viewModel.getEstates().observe({ lifecycle }) { list ->
            Logger.i("Loaded ${list.size} estates")
        }
    }

}
