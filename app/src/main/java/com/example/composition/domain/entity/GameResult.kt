package com.example.composition.domain.entity

data class GameResult (
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val totalQuestions: Int,
    val gameSettings: GameSettings
        )