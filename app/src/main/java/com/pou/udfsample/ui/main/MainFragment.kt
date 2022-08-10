package com.pou.udfsample.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.pou.udfsample.R
import com.pou.udfsample.databinding.FragmentMainBinding
import com.pou.udfsample.ui.main.common.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    companion object {
        fun newInstance() = MainFragment()
    }

    override var bindingInflater: (View) -> FragmentMainBinding = FragmentMainBinding::bind


    private val viewModel: MainViewModel by viewModels(factoryProducer = {
        MainViewModelFactory(ServiceLocator.repository)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                view.context,
                DividerItemDecoration.VERTICAL
            ).apply {
                ContextCompat.getDrawable(requireContext(), R.drawable.divider_10dp)
                    ?.let { setDrawable(it) }
            }
        )
        if (binding.recyclerView.adapter == null) {
            binding.recyclerView.adapter = FruitsAdapter()
        }
        binding.swipeRefresh.setOnRefreshListener {
            refresh()
        }
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }

    private fun refresh() {
        viewModel.getAllFruitsList().observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it.isLoading
            (binding.recyclerView.adapter as FruitsAdapter).submitList(it.data)
        }
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

}