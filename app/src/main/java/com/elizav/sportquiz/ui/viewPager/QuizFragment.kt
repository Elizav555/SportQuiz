package com.elizav.sportquiz.ui.viewPager

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.elizav.sportquiz.databinding.FragmentQuizBinding
import com.elizav.sportquiz.domain.model.QuizItem
import com.elizav.sportquiz.ui.home.HomeFragment
import com.elizav.sportquiz.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class QuizFragment(private val quizItem: QuizItem,private val onAnswer:(Boolean)->Unit) : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    private var buttons = ArrayList<Button>(4)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (i in 0 until 4) {
            val id = resources.getIdentifier("btn_answer_$i", "id", activity?.packageName)
            buttons.add(view.findViewById<Button>(id))
        }
        with(binding) {
            tvQuestion.text = quizItem.question
            val correctId = Random.nextInt(0, 4)
            val incorrectAnswers = quizItem.incorrectAnswers.toMutableList()
            for (i in 0 until 4) {
                if (i == correctId) {
                    configureBtn(correctId, quizItem.correctAnswer) { onCorrectAnswer(correctId) }
                } else {
                    configureBtn(i, incorrectAnswers.removeFirst()) { onWrongAnswer(i) }
                }
            }
        }
    }

    private fun onCorrectAnswer(id: Int) {
        buttons.getOrNull(id)?.setBackgroundColor(Color.GREEN)
        deactivateButtons()
       onAnswer(true)
    }

    private fun onWrongAnswer(id: Int) {
        buttons.getOrNull(id)?.setBackgroundColor(Color.RED)
        deactivateButtons()
        onAnswer(false)
    }

    private fun deactivateButtons() {
        buttons.forEach { it.isEnabled = false }
    }

    private fun configureBtn(id: Int, btnText: String, onClick: () -> Unit) {
        buttons.getOrNull(id)?.apply {
            text = btnText
            setOnClickListener {
                onClick()
            }
        }
    }
}