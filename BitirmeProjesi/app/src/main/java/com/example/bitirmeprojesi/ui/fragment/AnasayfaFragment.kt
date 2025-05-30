package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitirmeprojesi.databinding.FragmentAnasayfaBinding
import com.example.bitirmeprojesi.ui.adapter.YemekAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private val viewModel: AnasayfaViewModel by viewModels()

    private var searchJob: Job? = null
    private lateinit var adapter: YemekAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)

        adapter = YemekAdapter(listOf())
        binding.rvYemekler.layoutManager = LinearLayoutManager(requireContext())
        binding.rvYemekler.adapter = adapter

        // Loading ve boş liste görünürlükleri başlangıçta gizli
        binding.progressBar.visibility = View.GONE
        binding.tvEmpty.visibility = View.GONE
        binding.tvError.visibility = View.GONE

        observeViewModel()

        setupSearchView()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.yemekDurumu.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is AnasayfaViewModel.Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvYemekler.visibility = View.GONE
                    binding.tvEmpty.visibility = View.GONE
                    binding.tvError.visibility = View.GONE
                }
                is AnasayfaViewModel.Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    if (resource.data.isNullOrEmpty()) {
                        binding.tvEmpty.visibility = View.VISIBLE
                        binding.rvYemekler.visibility = View.GONE
                    } else {
                        binding.tvEmpty.visibility = View.GONE
                        binding.rvYemekler.visibility = View.VISIBLE
                        adapter.updateList(resource.data)
                    }
                    binding.tvError.visibility = View.GONE
                }
                is AnasayfaViewModel.Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvError.visibility = View.VISIBLE
                    binding.tvError.text = resource.message ?: "Bir hata oluştu"
                    binding.rvYemekler.visibility = View.GONE
                    binding.tvEmpty.visibility = View.GONE
                }

                else -> {}
            }
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    delay(500) // 500ms debounce
                    viewModel.ara(newText)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                searchJob?.cancel()
                viewModel.ara(query)
                return true
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.yemekleriYukle()
    }
}
