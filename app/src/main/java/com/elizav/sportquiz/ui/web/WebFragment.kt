package com.elizav.sportquiz.ui.web

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.elizav.sportquiz.databinding.FragmentWebBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebFragment() : Fragment() {
    private lateinit var binding: FragmentWebBinding
    private val webViewModel: WebViewModel by viewModels()
    private val args: WebFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.webView) {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
                OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (canGoBack()) {
                        goBack()
                    } else {
                        findNavController().popBackStack()
                    }
                }
            })
        }

        webViewModel.saveUrl(args.url)
        val webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showLoading(true)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                showLoading(false)
            }
        }
        binding.webView.apply {
            loadUrl(args.url)
            this.webViewClient = webViewClient
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }
}