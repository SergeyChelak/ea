package org.chelak.ea.screens.estate.calculation.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import org.chelak.ea.R

class RuleReviewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.rule_review, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.review_save -> {
                // TODO handle
                true
            }
            R.id.review_delete -> {
                // TODO handle
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


}