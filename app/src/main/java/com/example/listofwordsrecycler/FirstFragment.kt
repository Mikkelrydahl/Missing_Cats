package com.example.listofwordsrecycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.listofwordsrecycler.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val wordsViewModel: WordsViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            val word = binding.editTextWord.text.trim().toString()
            if (word.isEmpty()) {
                binding.editTextWord.error = "No word"
                return@setOnClickListener
            }
            wordsViewModel.add(word)
        }

        binding.ClearButton.setOnClickListener {
            wordsViewModel.clear()
        }

        wordsViewModel.words.observe(viewLifecycleOwner) { words ->
            if (words.isEmpty()) {
                binding.textViewMessage.text = "No words"
            } else {
                binding.textViewMessage.text = words.toString()
            }
        }

        binding.ShowWords.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.recyclerView.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_fragment_third)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}