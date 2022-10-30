package com.elizav.sportquiz.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.elizav.sportquiz.R
import com.elizav.sportquiz.databinding.FragmentHomeBinding
import com.elizav.sportquiz.domain.model.Command
import com.elizav.sportquiz.domain.model.QuizItem
import com.elizav.sportquiz.ui.viewPager.QuizAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var quizAdapter: QuizAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        quizAdapter = QuizAdapter(this) { isCorrect: Boolean -> homeViewModel.onAnswer(isCorrect) }
        binding.viewPager.apply {
            adapter = quizAdapter
            isUserInputEnabled = false
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getString(R.string.question, position + 1)
            tab.view.isClickable = false
        }.attach()
        initObservers()

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_restart -> {
                        homeViewModel.getQuizItems()
                        return true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner)
        menuHost.invalidateMenu()
    }

    private fun initObservers() = with(homeViewModel) {
        quizItems.observe(viewLifecycleOwner) { quizList ->
            submitQuizItems(quizList)
        }
        commands.observe(viewLifecycleOwner) {
            when (it) {
                is Command.HandleLoading -> showLoading(it.isLoading)
                is Command.ShowEndGame -> showEndGameDialog(it.score)
                is Command.ShowError -> showSnackbar(it.message)
                is Command.NextQuestion -> {
                    val color = if (it.isCorrect) Color.GREEN else Color.RED
                    binding.tabLayout.getTabAt(it.newQuestion - 1)?.view?.setBackgroundColor(color)
                    binding.viewPager.currentItem = it.newQuestion
                }
            }
        }
    }

    private fun submitQuizItems(quizList: List<QuizItem>) {
        quizAdapter.submitList(quizList)
        showLoading(false)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    private fun showEndGameDialog(score: Int) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_end_title))
            .setMessage(getString(R.string.dialog_end_message, score))
            .setPositiveButton(
                getString(R.string.new_game)
            ) { dialog, _ ->
                homeViewModel.getQuizItems()
                dialog.dismiss()

            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog?.cancel()
            }
            .create()
        dialog.show()
    }

    private fun showSnackbar(text: String) = Snackbar.make(
        binding.root,
        text,
        Snackbar.LENGTH_LONG
    ).show()
}