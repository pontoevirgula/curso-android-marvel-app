package com.example.marvelapp.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null
    private val binding: FragmentCharacterBinding get() = _binding!!
    private val viewModel: CharactersViewModel by viewModels()

    private val charactersAdapter by lazy {
        CharactersAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharacterBinding.inflate(
        inflater, container, false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharacterAdapter()
        lifecycleScope.launch {
            viewModel.charactersPagingData("").collect{ pagingData ->
                charactersAdapter.submitData(pagingData)
            }
        }
    }

    private fun initCharacterAdapter() {
        with(binding.recyclerCharacters) {
            scrollToPosition(0)
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = charactersAdapter
        }
    }


}