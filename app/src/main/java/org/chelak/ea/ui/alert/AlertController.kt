package org.chelak.ea.ui.alert

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.button_alert.view.*
import kotlinx.android.synthetic.main.estate_list_fragment.*
import kotlinx.android.synthetic.main.fragment_alert.*
import kotlinx.android.synthetic.main.fragment_alert.view.*
import kotlinx.android.synthetic.main.fragment_alert.view.actionsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.chelak.ea.R
import org.chelak.ea.ui.list.clickPosition
import org.chelak.ea.ui.list.setHorizontalLayout
import org.chelak.ea.ui.list.setVerticalLayout
import java.util.concurrent.atomic.AtomicLong
import kotlin.collections.set


class AlertController(private val navController: NavController) {

    companion object {
        internal const val ALERT_ID = "alert_id"
    }

    fun present(alert: Alert) {
        val owner = navController.getViewModelStoreOwner(R.id.root_nav_graph)
        val viewModel = ViewModelProvider(owner).get(AlertViewModel::class.java)
        val alertId = viewModel.put(alert)
        val params = Bundle()
        params.putLong(ALERT_ID, alertId)
        navController.navigate(R.id.alertDialog, params)
    }

    class AlertViewModel : ViewModel() {

        private val alertId = AtomicLong(0)
        private var alerts : MutableMap<Long, Alert> = mutableMapOf()

        fun put(alert: Alert): Long {
            val id = alertId.addAndGet(1)
            alerts[id] = alert
            return id
        }

        fun alert(id: Long): LiveData<Alert?> {
            val liveData = MutableLiveData<Alert>()
            liveData.value = alerts[id]
            return liveData
        }

        fun handle(id: Long, actionIndex: Int) {
            alerts[id]?.let {
                it.action(actionIndex)?.handler?.invoke()
                alerts.remove(id)
            }
        }
    }

    class AlertFragment : DialogFragment() {
        private val viewModel: AlertViewModel by navGraphViewModels(R.id.root_nav_graph)

        private val alertId: Long get() = arguments?.getLong(ALERT_ID) ?: 0

        private val adapter = object : RecyclerView.Adapter<ActionViewHolder>() {
            private lateinit var items: List<Alert.Action>

            fun update(actions: List<Alert.Action>) {
                items = actions
                notifyDataSetChanged()
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionViewHolder = ActionViewHolder.instance(parent)

            override fun getItemCount(): Int {
                return items.size
            }

            override fun onBindViewHolder(holder: ActionViewHolder, position: Int) {
                val action = items[position]
                holder.setText(action.title)
                holder.setStyle(action.style)
            }

        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? = inflater.inflate(R.layout.fragment_alert, container, false)

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
//            actionsList.setHorizontalLayout()
            actionsList.adapter = adapter
            actionsList.clickPosition().observe(viewLifecycleOwner, Observer {
                viewModel.handle(alertId, it)
                dismiss()
            })
            viewModel.alert(alertId).observe(viewLifecycleOwner, Observer {
                val alert = it!!
                labelTitle.text = alert.title
                labelMessage.text = alert.message
                val actions = alert.getActions()
                if (actions.size > 2) actionsList.setVerticalLayout() else actionsList.setHorizontalLayout()
                adapter.update(actions)
            })
        }

        class ActionViewHolder(view: View): RecyclerView.ViewHolder(view) {

            companion object {
                fun instance(parent: ViewGroup): ActionViewHolder {
                    val inflater = LayoutInflater.from(parent.context)
                    val view = inflater.inflate(R.layout.button_alert, parent, false)
                    return ActionViewHolder(view)
                }
            }

            private val button: Button = view.button

            fun setText(text: String?) {
                button.text = text
            }

            fun setStyle(style: Alert.Action.Style) {
                button.setTypeface(null, if (style == Alert.Action.Style.dismiss) Typeface.BOLD else Typeface.NORMAL)
            }
        }

    }

}