package org.chelak.ea.screens.estate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import org.chelak.ea.R

class MeterDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = MeterDetailsFragment()
    }

    private lateinit var viewModel: MeterDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.meter_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MeterDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
