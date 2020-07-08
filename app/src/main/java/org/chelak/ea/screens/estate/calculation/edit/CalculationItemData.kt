package org.chelak.ea.screens.estate.calculation.edit

import org.chelak.ea.common.BitMask
import org.chelak.ea.ui.list.SimpleListItem

class CalculationItemData(var estateId: Long) {

    var tariff: SimpleListItem? = null

    var meters: List<SimpleListItem>? = null

    var rates: List<SimpleListItem>? = null

    var monthMask: BitMask? = null

}