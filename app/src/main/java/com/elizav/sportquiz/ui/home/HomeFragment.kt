package com.elizav.sportquiz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.elizav.sportquiz.R
import com.elizav.sportquiz.databinding.FragmentHomeBinding
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
    private var score = 0
//TODO swipe programmaticly only and show if question was correct
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        quizAdapter = QuizAdapter(this)
        binding.viewPager.apply {
            adapter = quizAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getString(R.string.question, position+1)
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
                        showLoading(true)
                        homeViewModel.getQuizItems()
                        return true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner)
        menuHost.invalidateMenu()
    }

    private fun initObservers() {
        homeViewModel.quizItems.observe(viewLifecycleOwner) { result ->
            result.fold(onSuccess = { quizList ->
                submitQuizItems(quizList)
            }, onFailure = {
                showSnackbar(it.message.toString())
            })
        }
    }

    fun addScorePoint() {
        score++
    }

    private fun submitQuizItems(quizList: List<QuizItem>) {
        quizAdapter.submitList(quizList)
        showLoading(false)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

//    private fun showEndGameDialog() = activity?.let {
//        showDialog(it, DialogParams(
//            title = getString(R.string.logout),
//            message = getString(R.string.message_logout),
//            submitBtnText = getString(R.string.yes),
//            submitOnClickListener = { _, _ ->
//                actions.onNext(HostAction.LogoutAction)
//            },
//            cancelOnClickListener = { dialog, _ ->
//                dialog?.cancel()
//            }
//        ))
//    }

    private fun showSnackbar(text: String) = Snackbar.make(
        binding.root,
        text,
        Snackbar.LENGTH_LONG
    ).show()
}