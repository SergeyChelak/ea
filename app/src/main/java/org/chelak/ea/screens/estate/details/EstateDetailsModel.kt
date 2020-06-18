package org.chelak.ea.screens.estate.details

enum class TrendDirection {
    UP,
    ZERO,
    DOWN
}

open class BriefComparisonInfo {
    var date: String = ""
    var value: String = ""
    var delta: String = ""
    var trendDirection: TrendDirection = TrendDirection.ZERO
}

class MeterBriefInfo : BriefComparisonInfo() {
    var uid: Long = 0
}