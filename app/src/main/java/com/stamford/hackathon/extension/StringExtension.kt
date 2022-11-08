package com.stamford.hackathon.extension

fun <P1 : String?, P2 : String?> whenAllNotBlank(
    p1: P1,
    p2: P2,
    func: (P1, P2) -> Unit
) {
    if (!p1.isNullOrBlank() && !p2.isNullOrBlank()) {
        func.invoke(p1, p2)
        return
    }
}

fun String?.whenNotBlank(func: (String) -> Unit) {
    if (this != null && this.isNotBlank()) {
        func.invoke(this)
    }
}