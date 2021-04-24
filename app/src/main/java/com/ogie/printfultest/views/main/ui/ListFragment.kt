package com.ogie.printfultest.views.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ogie.printfultest.R
import com.ogie.printfultest.databinding.FragmentListBinding
import com.ogie.printfultest.utils.ApiResponse
import com.ogie.printfultest.utils.hide
import com.ogie.printfultest.utils.show
import com.ogie.printfultest.utils.toast
import com.ogie.printfultest.views.main.adapter.ListClickListener
import com.ogie.printfultest.views.main.adapter.ListRecyclerAdapter
import com.ogie.printfultest.views.main.viewModel.ListViewModel

class ListFragment : Fragment() {

    private lateinit var binding : FragmentListBinding

    private lateinit var adapter : ListRecyclerAdapter

    private val viewModel : ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListRecyclerAdapter(requireContext(), ListClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToListDetailsFragment(it.id))
        })

        binding.recyclerView.adapter = adapter

        viewModel.list.observe(viewLifecycleOwner, {
            when (it) {
                is ApiResponse.Loading -> {
                    binding.progressBar.show()
                    binding.retryBtn.hide()
                }
                is ApiResponse.Success -> {
                    binding.progressBar.hide()
                    binding.retryBtn.hide()
                    adapter.submitList(it.data.rickMorties)
                }
                is ApiResponse.Failure -> {
                    binding.progressBar.hide()
                    if(adapter.currentList.isEmpty()){
                        requireContext().toast(it.message)
                        binding.retryBtn.show()
                        binding.retryBtn.setOnClickListener {
                            viewModel.fetchList()
                        }
                    }else{
                        binding.retryBtn.hide()
                    }
                }
            }
        })
    }

}