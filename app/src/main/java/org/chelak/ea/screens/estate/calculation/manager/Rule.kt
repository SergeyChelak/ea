package org.chelak.ea.screens.estate.calculation.manager

import org.chelak.ea.common.BitMask
import org.chelak.ea.ui.list.SimpleListItem

class Rule(var uid: Long) {

    var estateId: Long = 0

    var tariff: SimpleListItem? = null

    var meters: List<SimpleListItem>? = null

    var rates: List<SimpleListItem>? = null

    var monthMask: BitMask? = null

    val isNew: Boolean
        get() = uid == 0L

}