package org.chelak.ea.core

import android.content.Context
import java.lang.ref.WeakReference

interface StringResource {

    fun getString(resId: Int): String

    fun getString(resId: Int, vararg formatArgs: Any?): String

}


class ResourceProvider(context: Context): StringResource {

    companion object {
        const val UNKNOWN = "<???>"
    }

    private val contextReference =  WeakReference(context)

    override fun getString(resId: Int): String {
        contextReference.get()?.let {
            return it.getString(resId)
        }
        return UNKNOWN
    }

    override fun getString(resId: Int, vararg formatArgs: Any?): String {
        contextReference.get()?.let {
            return it.getString(resId, formatArgs)
        }
        return UNKNOWN
    }

}