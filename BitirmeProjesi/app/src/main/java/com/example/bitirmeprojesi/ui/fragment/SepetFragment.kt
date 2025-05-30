package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitirmeprojesi.databinding.FragmentSepetBinding
import com.example.bitirmeprojesi.ui.adapter.SepetAdapter
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {

    private var _binding: FragmentSepetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SepetViewModel by viewModels()
    private lateinit var sepetAdapter: SepetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSepetBinding.inflate(inflater, container, false)

        binding.rvSepetUrunler.layoutManager = LinearLayoutManager(requireContext())

        sepetAdapter = SepetAdapter(emptyList())
        binding.rvSepetUrunler.adapter = sepetAdapter

        binding.btnSiparisiTamamla.setOnClickListener {
            viewModel.siparisiTamamla()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.sepetListesi.observe(viewLifecycleOwner) { liste ->
            sepetAdapter.updateList(liste)
        }

        viewModel.toplamFiyat.observe(viewLifecycleOwner) { toplam ->
            binding.tvToplamFiyat.text = "Toplam: $toplam ₺"
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepetiYukle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
