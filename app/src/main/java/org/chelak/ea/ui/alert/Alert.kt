package org.chelak.ea.ui.alert

data class Alert(
    var title: String? = null,
    var message: String? = null) {

    private var actions = mutableListOf<Action>()

    data class Action(
        val title: String,
        val style: Style = Style.default,
        val handler: (() -> Unit)? = null
    ) {
        enum class Style {
            default,
            dismiss
        }
    }

    fun add(action: Action) {
        actions.add(action)
    }

    fun getActions(): List<Action> = actions

    fun action(position: Int): Action? =
        if (position < actions.size)
            actions[position]
        else
            null

}