package org.chelak.ea.screens.estate.details

import androidx.lifecycle.MediatorLiveData
import org.chelak.ea.core.Repository
import javax.inject.Inject

enum class TrendDirection {
    UP,
    ZERO,
    DOWN
}

data class BriefComparisonInfo(
    val date: String,
    val value: String,
    val delta: String,
    val trendDirection: TrendDirection = TrendDirection.ZERO
)

class MeterBriefInfo (
    val uid: Long,
    val title: String
    //val comparisonInfo: BriefComparisonInfo
)

class EstateDetailsModel {
    var estateId: Long = 0
    var estateTitle: String = ""
    var payment: BriefComparisonInfo? = null
    var meters: List<BriefComparisonInfo>? = null
}

class EstateLiveData(): MediatorLiveData<EstateDetailsModel>() {

    @Inject
    lateinit var repository: Repository




}