package org.chelak.ea.screens.estate.calculation.editor.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.chelak.ea.R
import org.chelak.ea.common.concatenatedString
import org.chelak.ea.core.StringResource
import org.chelak.ea.screens.estate.calculation.editor.RuleEditor
import org.chelak.ea.ui.AlertEmitter
import org.chelak.ea.ui.VoidHandler
import org.chelak.ea.ui.dialog.AlertModel
import org.chelak.ea.ui.list.SimpleListItem
import javax.inject.Inject

class RuleReviewViewModel : ViewModel(), AlertEmitter {
    @Inject
    lateinit var ruleEditor: RuleEditor
    @Inject
    lateinit var stringResource: StringResource

    override var alertData = MutableLiveData<AlertModel>()
        private set

    val data: LiveData<List<RuleItem>>
        get() {
            val rule = ruleEditor.rule
            val items: MutableList<RuleItem> = mutableListOf()
            rule.tariff?.let {
                items.add(
                    RuleItem(
                        title = stringResource.getString(R.string.review_cell_title_tariff),
                        details = it.title,
                        handler = {
                            ruleEditor.editTariff()
                        }
                    )
                )
            }
            rule.meters?.let { list ->
                val details = if (list.isEmpty()) {
                    stringResource.getString(R.string.review_cell_value_not_accessible)
                } else {
                    (list.map { it.title }).concatenatedString("\n")
                }
                items.add(
                    RuleItem(
                        title = stringResource.getString(R.string.review_cell_title_meters),
                        details = details,
                        handler = {
                            ruleEditor.editMeters()
                        }
                    )
                )
            }
            rule.rates?.let { list ->
                val details = if (list.isEmpty()) {
                    stringResource.getString(R.string.review_cell_value_not_accessible)
                } else {
                    (list.map { it.title }).concatenatedString("\n")
                }
                items.add(RuleItem(title = stringResource.getString(R.string.review_cell_title_rates),
                    details = details,
                    handler = {
                        ruleEditor.editRates()
                    }
                ))
            }
            rule.monthMask?.let { mask ->
                val details = when (mask.raw) {
                    0 -> stringResource.getString(R.string.review_cell_value_never)
                    0xfff -> stringResource.getString(R.string.review_cell_value_whole_year)     // 12 month
                    else -> {
                        val count = (0..11).fold(0) { sum, elem ->
                            sum + mask[elem].toInt()
                        }
                        stringResource.getString(R.string.review_cell_value_n_months_1, count)
                    }
                }
                items.add(
                    RuleItem(title = stringResource.getString(R.string.review_cell_title_active_period),
                        details = details,
                        handler = {
                            ruleEditor.editMonth()
                        })
                )
            }
            return MutableLiveData(items)
        }

    fun save() {
        ruleEditor.save()
    }

    fun delete() {
        alertData.value = AlertModel(
            title = stringResource.getString(R.string.dialog_title_warning),
            message = stringResource.getString(R.string.dialog_confirm_undone_action),
            positiveTitle = stringResource.getString(R.string.btn_yes),
            positiveAction = {
                ruleEditor.delete()
                alertData.value = null
            },
            negativeTitle = stringResource.getString(R.string.btn_no),
            negativeAction = { alertData.value = null }
        )
    }

}

class RuleItem(title: String, var details: String? = null, var handler: VoidHandler? = null) :
    SimpleListItem(uid = 0, title = title)

internal fun Boolean.toInt() : Int = if (this) 1 else 0