package com.pou.udfsample.ui.main.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding>(@LayoutRes id: Int) : Fragment(id) {

    private var _binding: T? = null
    val binding: T
        get() = _binding!!
    abstract var bindingInflater: (View) -> T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        _binding = bindingInflater.invoke(view!!)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}