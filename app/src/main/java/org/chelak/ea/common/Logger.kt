package org.chelak.ea.common

import android.util.Log
import java.util.*

class Logger internal constructor() {

    enum class Level(val value: Int) {
        VERBOSE(0),
        DEBUG(1),
        WARNING(2),
        ERROR(3),
        PRODUCTION(4)
    }

    private val globalLevel = Level.VERBOSE
    private val tag = "EA"

    internal fun isMessageVisible(level: Level): Boolean = globalLevel.value <= level.value

    companion object {

        private val logger = Logger()

        private fun formatString(format: String, vararg args: Any): String {
            return if (args.isNotEmpty()) String.format(Locale.US, format, args) else format
        }

        fun i(format: String, vararg args: Any) {
            if (logger.isMessageVisible(Level.VERBOSE)) {
                Log.i(logger.tag, formatString(format, args))
            }
        }

        fun d(format: String, vararg args: Any) {
            if (logger.isMessageVisible(Level.DEBUG)) {
                Log.d(logger.tag, formatString(format, args))
            }
        }

        fun w(format: String, vararg args: Any) {
            if (logger.isMessageVisible(Level.WARNING)) {
                Log.w(logger.tag, formatString(format, args))
            }
        }

        fun e(format: String, vararg args: Any) {
            if (logger.isMessageVisible(Level.ERROR)) {
                Log.e(logger.tag, formatString(format, args))
            }
        }

    }

}