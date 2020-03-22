package org.chelak.ea.ui

import android.os.Bundle

class ArgumentContainer(private var bundle: Bundle) {

    companion object {
        internal const val ESTATE_ID = "estate_id"
        fun builder() = Builder()
    }

    fun getBundle(): Bundle = bundle

    fun getEstateId(): Long = bundle.getLong(ESTATE_ID)

    class Builder {
        private var bundle = Bundle()

        fun putEstateId(estateId: Long): Builder {
            bundle.putLong(Companion.ESTATE_ID, estateId)
            return this
        }

        fun build(): ArgumentContainer = ArgumentContainer(bundle)
    }

}