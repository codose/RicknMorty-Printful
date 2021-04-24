package com.ogie.printfultest.views.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ogie.printfultest.R
import com.ogie.printfultest.databinding.FragmentListDetailsBinding
import com.ogie.printfultest.model.RickMorty
import com.ogie.printfultest.utils.*
import com.ogie.printfultest.views.main.viewModel.ListViewModel


class ListDetailsFragment : Fragment() {

    private lateinit var binding : FragmentListDetailsBinding

    private val viewModel : ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListDetailsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = ListDetailsFragmentArgs.fromBundle(requireArguments()).id
        viewModel.fetchItem(id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setObservers() {
        viewModel.item.observe(viewLifecycleOwner, {
            when (it) {
                is ApiResponse.Loading -> {
                    binding.progress.root.show()
                }
                is ApiResponse.Success -> {
                    binding.progress.root.hide()
                    loadUI(it.data)
                }
                is ApiResponse.Failure -> {
                    binding.progress.root.hide()
                    requireContext().toast(it.message)
                    findNavController().navigateUp()
                }
            }
        })
    }

    private fun loadUI(data : RickMorty){
        binding.characterName.text = data.name
        binding.appearance.text = "${data.episode.size} Episode(s)"
        binding.characterStatus.text = data.status
        binding.species.text = data.species
        binding.location.text = data.location.name
        binding.createdAt.text = data.created.toDateTimeString()
        Glide.with(requireContext())
            .load(data.image)
            .placeholder(R.drawable.placeholder)
            .into(binding.image)
    }

}