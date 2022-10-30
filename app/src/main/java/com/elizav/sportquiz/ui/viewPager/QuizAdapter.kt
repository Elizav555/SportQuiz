package com.elizav.sportquiz.ui.viewPager

import androidx.fragment.app.Fragment
import com.elizav.sportquiz.domain.model.QuizItem

class QuizAdapter(fragment: Fragment) :
    DiffFragmentStateAdapter<QuizItem>(fragment, QuizItem.DiffCallback()) {
    override fun createFragment(position: Int): Fragment {
        val quizItem = getCurrentList().getOrNull(position)
            ?: throw IllegalArgumentException()
        return QuizFragment(quizItem)
    }

    override fun getItemId(position: Int): Long =
        getItem(position).id

    override fun containsItem(itemId: Long): Boolean =
        getCurrentList().map { it.id }.contains(itemId)
}