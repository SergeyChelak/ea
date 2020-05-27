package org.chelak.ea.ui.list

import android.content.Context
import android.view.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.ViewHolder.getContext(): Context = itemView.context


fun RecyclerView.clickPosition(): LiveData<Int> {

    class TouchListener(context: Context) : RecyclerView.OnItemTouchListener {

        private val listener = object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent?): Boolean = true
        }
        private val gestureDetector = GestureDetector(context, listener)

        private val emitter = MutableLiveData<Int>()

        fun getEmitter() = emitter

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            rv.findChildViewUnder(e.x, e.y)?.let {
                emitter.value = rv.getChildAdapterPosition(it)
            }
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            return if (gestureDetector.onTouchEvent(e)) {
                onTouchEvent(rv, e)
                true
            } else false
        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            //
        }
    }

    val touchListener = TouchListener(context)
    addOnItemTouchListener(touchListener)
    return touchListener.getEmitter()

}


fun inflateView(parent: ViewGroup, layoutId: Int): View {
    val inflater = LayoutInflater.from(parent.context)
    return inflater.inflate(layoutId, parent, false)
}


fun RecyclerView.setVerticalLayout() {
    layoutManager = LinearLayoutManager(context).apply {
        orientation = LinearLayoutManager.VERTICAL
    }
}


fun RecyclerView.setHorizontalLayout() {
    layoutManager = LinearLayoutManager(context).apply {
        orientation = LinearLayoutManager.HORIZONTAL
    }
}


fun RecyclerView.setVerticalGridLayout(spanCount: Int = 2) {
    layoutManager = GridLayoutManager(context, spanCount, GridLayoutManager.VERTICAL, false)
}