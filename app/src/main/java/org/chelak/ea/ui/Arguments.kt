package org.chelak.ea.ui

import android.os.Bundle

internal object BundleKeys {
    const val estateId = "estate_id"
    const val tariffId = "tariff_id"
    const val meterId = "meter_id"
    const val navigationTitle = "navigation_title"
    const val stepId = "step_id"
    const val isMultipleChoice = "is_multiple_choice"
}

var Bundle.estateId: Long
    get() = getLong(BundleKeys.estateId)
    set(value) = putLong(BundleKeys.estateId, value)

var Bundle.tariffId: Long
    get() = getLong(BundleKeys.tariffId)
    set(value) = putLong(BundleKeys.tariffId, value)

var Bundle.meterId: Long
    get() = getLong(BundleKeys.meterId)
    set(value) = putLong(BundleKeys.meterId, value)


var Bundle.navigationTitle: String
    get() = getString(BundleKeys.navigationTitle) ?: "!!EMPTY!!"
    set(value) = putString(BundleKeys.navigationTitle, value)

var Bundle.stepIdentifier: Long
    get() = getLong(BundleKeys.stepId)
    set(value) = putLong(BundleKeys.stepId, value)


var Bundle.isMultipleChoice: Boolean
    get() = getBoolean(BundleKeys.isMultipleChoice)
    set(value) = putBoolean(BundleKeys.isMultipleChoice, value)