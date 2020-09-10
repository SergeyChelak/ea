package org.chelak.ea.screens.estate.calculation.editor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.chelak.ea.R
import org.chelak.ea.common.BitMask
import org.chelak.ea.core.Repository
import org.chelak.ea.core.StringResource
import org.chelak.ea.ui.Navigator
import org.chelak.ea.ui.list.SimpleListItem

class RuleEditor(
    val repository: Repository,
    val navigator: Navigator,
    val stringResource: StringResource
) {

    internal object StepIdentifier {
        const val tariffList = 100L
        const val meterList = 101L
        const val rateList = 102L
        const val monthMask = 103L
    }

    private lateinit var rule: Rule

    public fun setRule(rule: Rule) {
        this.rule = rule
    }

    public fun start() {
        if (rule.isNew) {
            next()
        } else {
            GlobalScope.launch {
                val item = repository.getPaymentSetting(rule.uid)
                rule.estateId = item.estateUid
                rule.monthMask = item.monthMask

                val tariff = repository.getTariff(item.tariffUid)
                rule.tariff = SimpleListItem(tariff.uid, tariff.title ?: "")

                rule.meters = repository.getMeterLinkList(rule.uid).map {
                    val meter = repository.getMeter(it.meterUid)
                    SimpleListItem(meter.uid, meter.title)
                }

                rule.rates = repository.getRateLinkList(rule.uid).map {
                    val rate = repository.getRate(it.rateUid)
                    SimpleListItem(rate.uid, rate.title ?: "")
                }
                withContext(Dispatchers.Main) {
                    next()
                }
            }
        }
    }

    public fun complete() {

    }

    public fun items(stepId: Long): List<SelectionListItem> =
        when (stepId) {
            StepIdentifier.tariffList -> {
                val selectedUid = rule.tariff?.uid ?: -1L
                repository.getTariffList().map {
                    SelectionListItem(it.uid, it.title ?: "", it.uid == selectedUid)
                }
            }
            StepIdentifier.meterList -> {
                val selected = mutableSetOf<Long>()
                rule.meters?.forEach {
                    selected.add(it.uid)
                }
                repository.getMeterList(rule.estateId).map {
                    SelectionListItem(it.uid, it.title, selected.contains(it.uid))
                }
            }
            StepIdentifier.rateList -> {
                val selected = mutableSetOf<Long>()
                rule.rates?.forEach { selected.add(it.uid) }
                repository.getRates(rule.estateId).map {
                    SelectionListItem(it.uid, it.title ?: "", selected.contains(it.uid))
                }
            }
            StepIdentifier.monthMask -> {
                val items = mutableListOf<SelectionListItem>()
                val monthList = stringResource.getMonthNames()
                for (i in monthList.indices) {
                    val isSelected = rule.monthMask?.get(i) ?: true
                    items.add(SelectionListItem(i.toLong(), monthList[i], isSelected))
                }
                items
            }
            else -> listOf()
        }


    public fun isValid(stepId: Long, items: List<SelectionListItem>): Boolean =
        when (stepId) {
            StepIdentifier.tariffList,
            StepIdentifier.monthMask -> items.containsSelection
            else -> true
        }

    public fun commit(stepId: Long, items: List<SelectionListItem>) {
        GlobalScope.launch {
            when (stepId) {
                StepIdentifier.tariffList -> {
                    rule.tariff = items.findLast { it.isSelected }
                }
                StepIdentifier.meterList -> {
                    rule.meters = items.filter { it.isSelected }
                }
                StepIdentifier.rateList -> {
                    rule.rates = items.filter { it.isSelected }
                }
                StepIdentifier.monthMask -> {
                    val mask = BitMask(0)
                    for (i in items.indices) {
                        mask[i] = items[i].isSelected
                    }
                    rule.monthMask = mask
                }
            }
            withContext(Dispatchers.Main) {
                next()
            }
        }
    }

    private fun save() {
        if (rule.isNew) {
            // TODO
        } else {
            repository.removeRateLinks(rule.uid)
            repository.removeMeterLinks(rule.uid)
        }
    }

    public fun next() {
        if (rule.tariff == null) {
            navigator.openSelectScreen(
                title = stringResource.getString(R.string.calculation_select_tariff),
                stepId = StepIdentifier.tariffList,
                isMultipleChoice = false
            )
        } else if (rule.meters == null) {
            navigator.openSelectScreen(
                title = stringResource.getString(R.string.calculation_select_meters),
                stepId = StepIdentifier.meterList,
                isMultipleChoice = true
            )
        } else if (rule.rates == null) {
            navigator.openSelectScreen(
                title = stringResource.getString(R.string.calculation_select_rates),
                stepId = StepIdentifier.rateList,
                isMultipleChoice = false
            )
        } else if (rule.monthMask == null) {
            navigator.openSelectScreen(
                title = stringResource.getString(R.string.calculation_select_month),
                stepId = StepIdentifier.monthMask,
                isMultipleChoice = true
            )
        } else {
            // TODO review step
        }
    }

}