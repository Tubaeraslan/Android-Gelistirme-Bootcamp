package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.databinding.FragmentDetayBinding
import com.example.bitirmeprojesi.ui.viewmodel.DetayViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetayFragment : Fragment() {

    private var _binding: FragmentDetayBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetayViewModel by viewModels()

    private var adet = 1
    private lateinit var gelenYemek: Yemekler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetayBinding.inflate(inflater, container, false)
        gelenYemek = arguments?.getParcelable("yemekNesnesi") ?: return binding.root

        binding.tvDetayYemekAdi.text = gelenYemek.yemek_adi
        binding.tvDetayYemekFiyat.text = "${gelenYemek.yemek_fiyat} ₺"

        Glide.with(requireContext())
            .load("http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}")
            .into(binding.ivDetayYemekResim)

        binding.tvAdet.text = adet.toString()

        binding.btnAzalt.setOnClickListener {
            if (adet > 1) {
                adet--
                binding.tvAdet.text = adet.toString()
            }
        }

        binding.btnArttir.setOnClickListener {
            adet++
            binding.tvAdet.text = adet.toString()
        }

        binding.btnSepeteEkle.setOnClickListener {
            viewModel.sepeteEkle(gelenYemek, adet)
            Snackbar.make(binding.root, "${adet} adet ${gelenYemek.yemek_adi} sepete eklendi!", Snackbar.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
