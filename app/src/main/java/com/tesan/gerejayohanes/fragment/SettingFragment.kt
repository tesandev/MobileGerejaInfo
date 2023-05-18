package com.tesan.gerejayohanes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tesan.gerejayohanes.R
import com.tesan.gerejayohanes.databinding.FragmentSettingBinding
import kotlinx.android.synthetic.main.activity_home.*

class SettingFragment:Fragment() {
    private lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        return binding.root

    }
}