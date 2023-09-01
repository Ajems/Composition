package com.example.composition.presentation.fragment

import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()
    private val gameResult by lazy {
        args.gameResult
    }

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("ChooseLevelFragment is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListener()
        bindView()
    }

    private fun bindView() {
        with(binding) {
            emojiResult.setImageResource(getSmileResId())

            tvRequiredAnswers.text = setText(
                R.string.required_score,
                gameResult.gameSettings.minCountOfRightAnswers
            )

            tvScoreAnswers.text = setText(
                R.string.score_answers,
                gameResult.countOfRightAnswers
            )

            tvTotalQuestions.text = setText(
                R.string.total_questions,
                gameResult.totalQuestions
            )

            tvRequiredPercentage.text = setText(
                R.string.required_percentage,
                gameResult.gameSettings.minPercentOfRightAnswer
            )

            tvScorePercentage.text = setText(
                R.string.score_percentage,
                gameResult.percentRightAnswers)
        }
    }

    private fun getSmileResId(): Int {
        return if (gameResult.winner)
            R.drawable.ic_smile
        else
            R.drawable.ic_sad
    }

    private fun setText(resId: Int, value: Int): String{
        return String.format(
            getString(resId),
            value.toString())
    }

    private fun setupClickListener() {
        binding.buttonRetry.setOnClickListener { retryGame() }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }
}