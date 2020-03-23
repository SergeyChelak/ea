package org.chelak.ea.ui.list

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

abstract class CommonListFragment: Fragment() {

    abstract fun getAdapter(): RecyclerView.Adapter<*>

}