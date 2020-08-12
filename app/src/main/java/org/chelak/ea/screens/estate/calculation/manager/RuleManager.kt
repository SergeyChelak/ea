package org.chelak.ea.screens.estate.calculation.manager

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.chelak.ea.core.Repository
import org.chelak.ea.ui.list.SimpleListItem
import javax.inject.Inject

class RuleManager(val rule: Rule) {

    @Inject
    lateinit var repository: Repository

    fun load() {
        if (rule.isNew) {
            return
        }
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
        }
    }

    fun save() {
        if (rule.isNew) {

        } else {
            repository.removeRateLinks(rule.uid)
            repository.removeMeterLinks(rule.uid)
        }
    }

    public fun next() {
        if (rule.tariff == null) {
            // TODO select tariff
        } else if (rule.meters == null) {
            // TODO select meters
        } else if (rule.rates == null) {
            // TODO select rates
        } else if (rule.monthMask == null) {
            // TODO select month
        }
    }

}