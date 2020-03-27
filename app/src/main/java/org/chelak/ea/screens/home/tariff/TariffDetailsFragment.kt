package org.chelak.ea.screens.home.tariff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import org.chelak.ea.R
import org.chelak.ea.ui.appComponent

class TariffDetailsFragment : Fragment() {

    private lateinit var viewModel: TariffDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tariff_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TariffDetailsViewModel::class.java)
        appComponent?.inject(viewModel)
    }

}
