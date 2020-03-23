package org.chelak.ea.common

enum class EstateType(val typeId: Int) {
    GENERAL(0),
    APARTMENT(1),
    TOWNHOUSE(2),
    COTTAGE(3),
    GARAGE(4);

    companion object {
        fun instance(typeId: Int): EstateType {
            return when (typeId) {
                1 -> APARTMENT
                2 -> TOWNHOUSE
                3 -> COTTAGE
                4 -> GARAGE
                else -> GENERAL
            }
        }
    }
}