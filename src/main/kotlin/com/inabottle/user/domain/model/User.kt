package com.inabottle.user.domain.model

import java.math.BigDecimal
import java.util.*

data class User(
        val id: UUID = UUID.randomUUID(),
        val email: String,
        val amountOfPoints: BigDecimal = BigDecimal.ZERO,
        val treasureHuntHistory: MutableList<TreasureHuntHistory> = mutableListOf()
)
