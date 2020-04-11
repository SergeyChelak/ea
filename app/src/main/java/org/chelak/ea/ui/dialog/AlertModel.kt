package org.chelak.ea.ui.dialog

import android.os.Bundle

open class AlertModel(bundle: Bundle? = null) {

    companion object {
        internal const val ALERT_ID = "alert_id"
        internal const val ALERT_TITLE = "alert_title"
        internal const val ALERT_MESSAGE = "alert_message"
        internal const val ALERT_POSITIVE_BUTTON = "positive_button"
        internal const val ALERT_NEGATIVE_BUTTON = "negative_button"
        internal const val ALERT_NEUTRAL_BUTTON = "neutral_button"
    }

    val bundle: Bundle = bundle ?: Bundle()


    var alertId: Long
        get() = bundle.getLong(ALERT_ID, 0)
        set(value) {
            bundle.putLong(ALERT_ID, value)
        }

    var title: String?
        get() = bundle.getString(ALERT_TITLE)
        set(value) {
            bundle.putString(ALERT_TITLE, value)
        }

    var message: String?
        get() = bundle.getString(ALERT_MESSAGE)
        set(value) {
            bundle.putString(ALERT_MESSAGE, value)
        }

    var positiveButton: String?
        get() = bundle.getString(ALERT_POSITIVE_BUTTON)
        set(value) {
            bundle.putString(ALERT_POSITIVE_BUTTON, value)
        }

    var negativeButton: String?
        get() = bundle.getString(ALERT_NEGATIVE_BUTTON)
        set(value) {
            bundle.putString(ALERT_NEGATIVE_BUTTON, value)
        }

    var neutralButton: String?
        get() = bundle.getString(ALERT_NEUTRAL_BUTTON)
        set(value) {
            bundle.putString(ALERT_NEUTRAL_BUTTON, value)
        }

}