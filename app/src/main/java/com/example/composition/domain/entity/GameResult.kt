package com.example.composition.domain.entity

import java.io.Serializable

data class GameResult (
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val totalQuestions: Int,
    val gameSettings: GameSettings
        ): Serializable