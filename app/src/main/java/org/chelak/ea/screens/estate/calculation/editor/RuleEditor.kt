package org.chelak.ea.screens.estate.calculation.editor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.chelak.ea.core.Repository
import org.chelak.ea.ui.Navigator
import org.chelak.ea.ui.list.SimpleListItem

class RuleEditor(val repository: Repository,
                 val navigator: Navigator) {

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
            navigator.openSelectTariff()
        } else if (rule.meters == null) {
            // TODO select meters
        } else if (rule.rates == null) {
            // TODO select rates
        } else if (rule.monthMask == null) {
            // TODO select month
        } else {
            // TODO review step
        }
    }

}