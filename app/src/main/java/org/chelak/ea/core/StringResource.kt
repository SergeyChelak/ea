package org.chelak.ea.core

import android.content.Context
import org.chelak.ea.R
import java.lang.ref.WeakReference

interface StringResource {

    fun getString(resId: Int): String

    fun getString(resId: Int, vararg formatArgs: Any): String

    fun getMonthNames(): List<String>
}


class ResourceProvider(context: Context) : StringResource {

    companion object {
        const val UNKNOWN = "<???>"
    }

    private val contextReference = WeakReference(context)

    override fun getString(resId: Int): String {
        contextReference.get()?.let {
            return it.getString(resId)
        }
        return UNKNOWN
    }

    override fun getString(resId: Int, vararg formatArgs: Any): String {
        contextReference.get()?.let {
            return it.getString(resId, *formatArgs)
        }
        return UNKNOWN
    }

    override fun getMonthNames(): List<String> =
        listOf(
            getString(R.string.month_january),
            getString(R.string.month_february),
            getString(R.string.month_march),
            getString(R.string.month_april),
            getString(R.string.month_may),
            getString(R.string.month_june),
            getString(R.string.month_july),
            getString(R.string.month_august),
            getString(R.string.month_september),
            getString(R.string.month_october),
            getString(R.string.month_november),
            getString(R.string.month_december)
        )


}