package com.example.composition.presentation.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

interface OnOptionClickListener{
    operator fun invoke(option: Int)
}

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

@BindingAdapter("visibleNumber")
fun bindVisibleNumber(textView: TextView, number: Int){
    textView.text = number.toString()
}

@BindingAdapter("sum")
fun bindSum(textView: TextView, sum: Int) {
    textView.text = sum.toString()
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean) {
    textView.setTextColor(getColorByState(textView.context, enough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean) {
    val color = getColorByState(progressBar.context, enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(context: Context, state: Boolean): Int {
    val colorResId = if (state){
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener){
    textView.setOnClickListener {
        clickListener(textView.text.toString().toInt())
    }
}
