package com.example.news_rxjava.ui.home

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.news_rxjava.databinding.FragmentHomeBinding
import com.example.news_rxjava.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun layoutInflater(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        observeViewModel()
    }

    private fun observeViewModel(){
        with(viewModel){
            news.observe(viewLifecycleOwner) {
                Log.e("salam2", it.pagination.toString())
            }
            loading.observe(viewLifecycleOwner) {
                Log.e("salam2", it.toString())
            }
            error.observe(viewLifecycleOwner) {
                Log.e("salam2", it)
            }
        }
    }

}