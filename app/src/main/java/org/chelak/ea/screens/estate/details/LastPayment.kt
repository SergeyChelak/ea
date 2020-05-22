package org.chelak.ea.screens.estate.details

import java.util.Date

enum class TrendDirection {
    UP,
    ZERO,
    DOWN
}

data class LastPayment(
        val date: Date?,
        val price: String?,
        val delta: String?,
        val trendDirection: TrendDirection = TrendDirection.ZERO
    )
