package com.stamford.hackathon.core

object RaiseAwarenessUtils {

    private val foodWasteFacts = listOf(
        "Up to 10% of global greenhouse gases comes from food that is produced, but not eaten.",
        "Almost half of all fruit and vegetables produced are wasted (that’s 3.7 trillion apples).",
        "One third of all food produced is lost or wasted –around 1.3 billion tonnes of food –costing the global economy close to $940 billion each year.",
        "Did you know that many countries took off the expiration date stickers from their products?",
        "Food that may not look as fresh still gives you all the nutrition that you need!"
    )

    fun showFoodWasteFacts(): String {
        val random = (foodWasteFacts.indices).random()
        return foodWasteFacts[random]
    }
}