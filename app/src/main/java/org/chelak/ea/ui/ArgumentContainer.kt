package org.chelak.ea.ui

import android.os.Bundle

class ArgumentContainer(bundle: Bundle?) {

    private var bundle: Bundle = bundle ?: Bundle()

    companion object {
        internal const val ESTATE_ID = "estate_id"
        internal const val TARIFF_ID = "tariff_id"
        fun builder() = Builder()
    }

    fun getBundle(): Bundle = bundle

    val estateId: Long get() = bundle.getLong(ESTATE_ID)

    val tariffId: Long get() = bundle.getLong(TARIFF_ID)

    class Builder {
        private var bundle = Bundle()

        fun putEstateId(estateId: Long): Builder {
            bundle.putLong(Companion.ESTATE_ID, estateId)
            return this
        }

        fun putTariffId(id: Long): Builder {
            bundle.putLong(Companion.TARIFF_ID, id)
            return this
        }

        fun build(): ArgumentContainer = ArgumentContainer(bundle)
    }

}