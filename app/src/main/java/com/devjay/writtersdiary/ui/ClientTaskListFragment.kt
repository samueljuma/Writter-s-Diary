package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devjay.writtersdiary.R
import com.devjay.writtersdiary.adpters.ClientTaskListAdapter
import com.devjay.writtersdiary.adpters.WriterTaskListAdapter
import com.devjay.writtersdiary.databinding.FragmentClientTaskListBinding
import com.devjay.writtersdiary.databinding.FragmentWriterTaskListBinding
import com.devjay.writtersdiary.viewmodels.ClientTaskListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientTaskListFragment : Fragment() {

    private lateinit var binding: FragmentClientTaskListBinding

    private val viewModel:ClientTaskListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientTaskListBinding.inflate(inflater,container,false)

        val adapter = ClientTaskListAdapter()

        binding.clientTasksList.adapter =adapter
        binding.viewModel = viewModel

        val arguments = ClientTaskListFragmentArgs.fromBundle(requireArguments())
        val clientId = arguments.clientId

        subscribeUI(adapter,binding,clientId)

        viewModel.navigateToAddClientTask.observe(viewLifecycleOwner,{
            if(it==true){
                this.findNavController().navigate(ClientTaskListFragmentDirections
                    .actionClientTaskListFragmentToAddClientTaskFragment())
                viewModel.doneNavigatingToAddTasks()
            }
        })
        return binding.root
    }

    private fun subscribeUI(adapter: ClientTaskListAdapter, binding: FragmentClientTaskListBinding, clientId: Long){
        viewModel.getAllClientsTasks(clientId).observe(viewLifecycleOwner){ result ->
            binding.hasTasks = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }

}