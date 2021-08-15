package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devjay.writtersdiary.R
import com.devjay.writtersdiary.databinding.FragmentUpdateWriterTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateWriterTaskFragment : Fragment() {

    private lateinit var binding: FragmentUpdateWriterTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUpdateWriterTaskBinding.inflate(inflater,container, false)

        return binding.root
    }

}