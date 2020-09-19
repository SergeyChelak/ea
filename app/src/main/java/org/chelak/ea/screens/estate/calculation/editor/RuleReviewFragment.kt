package org.chelak.ea.screens.estate.calculation.editor

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.chelak.ea.R
import org.chelak.ea.ui.MainActivity
import org.chelak.ea.ui.observeAlerts

class RuleReviewFragment : Fragment() {

    val viewModel: RuleReviewViewModel by lazy {
        ViewModelProvider(this).get(RuleReviewViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.component?.inject(viewModel)
        view?.let {
            observeAlerts(viewModel)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.rule_review, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.review_save -> {
                viewModel.save()
                true
            }
            R.id.review_delete -> {
                viewModel.delete()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


}