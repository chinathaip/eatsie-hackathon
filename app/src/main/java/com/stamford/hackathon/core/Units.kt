package com.stamford.hackathon.core

object Units {
    fun assign(category: String): String {
       return when(category) {
            Const.CATEGORY_DAIRIES -> "ml"
            else -> "g"
        }
    }
}