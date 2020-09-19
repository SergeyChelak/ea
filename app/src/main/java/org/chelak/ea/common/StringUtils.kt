package org.chelak.ea.common

fun Collection<String>.concatenatedString(separator: String = ""): String =
    if (this.isEmpty()) {
        ""
    } else {
        val format = String.format("%%s%s%%s", separator)
        val iterator = iterator()
        var result = iterator.next()
        while (iterator.hasNext()) {
            result = String.format(format, result, iterator.next())
        }
        result
    }
