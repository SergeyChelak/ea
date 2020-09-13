package org.chelak.ea.screens.estate.calculation.editor

import org.chelak.ea.common.BitMask
import org.chelak.ea.ui.list.SimpleListItem


typealias SelectItemList = List<SelectionListItem>

val SelectItemList.containsSelection: Boolean
    get() {
        for (item in this) {
            if (item.isSelected)
                return true
        }
        return false
    }

class Rule(
    var uid: Long,
    var estateId: Long
) {

    var tariff: SimpleListItem? = null

    var meters: List<SimpleListItem>? = null

    var rates: List<SimpleListItem>? = null

    var monthMask: BitMask? = null

    val isLoaded: Boolean
        get() =
            tariff != null && monthMask != null && meters != null && rates != null


}

class SelectionListItem(uid: Long, title: String, var isSelected: Boolean) :
    SimpleListItem(uid, title)

