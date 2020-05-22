package org.chelak.ea.ui

import android.os.Bundle

internal object BundleKeys {
    internal const val ESTATE_ID = "estate_id"
    internal const val TARIFF_ID = "tariff_id"
    internal const val METER_ID = "meter_id"
}

var Bundle.estateId: Long
    get() = getLong(BundleKeys.ESTATE_ID)
    set(value) {
        putLong(BundleKeys.ESTATE_ID, value)
    }

var Bundle.tariffId: Long
    get() = getLong(BundleKeys.TARIFF_ID)
    set(value) {
        putLong(BundleKeys.TARIFF_ID, value)
    }

var Bundle.meterId: Long
    get() = getLong(BundleKeys.METER_ID)
    set(value) {
        putLong(BundleKeys.METER_ID, value)
    }