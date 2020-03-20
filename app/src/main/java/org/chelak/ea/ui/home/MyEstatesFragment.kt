package org.chelak.ea.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.my_estates_fragment.*

import org.chelak.ea.R

class MyEstatesFragment : Fragment() {

    companion object {
        fun newInstance() = MyEstatesFragment()
    }

    private lateinit var viewModel: MyEstatesViewModel

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
                findNavController().navigate(R.id.action_myEstatesFragment_to_estateDetailsFragment)
            }
        }

        viewModel = ViewModelProvider(this).get(MyEstatesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
