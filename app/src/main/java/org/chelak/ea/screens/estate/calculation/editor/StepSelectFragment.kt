package org.chelak.ea.screens.estate.calculation.editor

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.R
import org.chelak.ea.ui.MainActivity
import org.chelak.ea.ui.dialog.presentAlert
import org.chelak.ea.ui.isMultipleChoice
import org.chelak.ea.ui.list.clickPosition
import org.chelak.ea.ui.list.setVerticalLayout
import org.chelak.ea.ui.navigationTitle
import org.chelak.ea.ui.stepIdentifier

class StepSelectFragment : Fragment() {

    private val adapter = StepSelectAdapter()

    val viewModel: StepSelectViewModel by lazy {
        ViewModelProvider(this).get(StepSelectViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter.isMultipleChoice = arguments?.isMultipleChoice ?: false
        (activity as? MainActivity)?.component?.inject(viewModel)
        viewModel.stepId = arguments?.stepIdentifier ?: 0L
        view?.let {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.setVerticalLayout()
            recyclerView.adapter = adapter
            recyclerView.clickPosition().observe(viewLifecycleOwner, Observer {
                adapter.onItemClick(it)
            })
        }
        viewModel.items.observe(viewLifecycleOwner, Observer {
            adapter.replace(it)
        })
        viewModel.alertData.observe(viewLifecycleOwner, Observer {
            it?.let {
                presentAlert(it)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.common_list_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        activity?.actionBar?.title = arguments?.navigationTitle
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.rule_step, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.step_next -> {
                viewModel.update(adapter.items)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

}