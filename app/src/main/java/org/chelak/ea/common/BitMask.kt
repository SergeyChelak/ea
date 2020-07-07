package org.chelak.ea.common

class BitMask(private var rawValue: Int) {

    constructor() : this(0)

    val raw: Int get() = rawValue

    operator fun get(index: Int): Boolean {
        val mask = 1 shl index
        return (rawValue and mask) == mask
    }

    operator fun set(index: Int, value: Boolean) {
        val mask = 1 shl index
        rawValue = if (value) rawValue or mask else rawValue and mask.inv()
    }

    override fun equals(other: Any?): Boolean {
        if (super.equals(other)) {
            return true
        }
        return rawValue == (other as? BitMask)?.raw
    }

    override fun hashCode(): Int {
        return rawValue
    }

    companion object {
        fun fromArray(array: List<Boolean>): BitMask {
            val mask = BitMask()
            for (i in array.indices) {
                mask[i] = array[i]
            }
            return mask
        }
    }
}