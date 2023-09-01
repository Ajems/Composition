package com.example.composition.presentation.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("totalQuestions")
fun bindTotalQuestions(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.total_questions),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)
    )
}

fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfRightAnswers == 0) {
        0
    } else {
        (countOfRightAnswers * 100 / totalQuestions.toDouble()).toInt()
    }
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getEmojiRes(winner))
}

private fun getEmojiRes(winner: Boolean): Int {
    return if (winner) R.drawable.ic_smile
    else R.drawable.ic_sad
}