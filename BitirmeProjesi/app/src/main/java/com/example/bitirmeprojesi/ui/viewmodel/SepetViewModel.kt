package com.example.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.repo.SepetRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor(
    private val sepetRepo: SepetRepo
) : ViewModel() {

    private val _sepetListesi = MutableLiveData<List<Pair<Yemekler, Int>>>()
    val sepetListesi: LiveData<List<Pair<Yemekler, Int>>> = _sepetListesi

    private val _toplamFiyat = MutableLiveData<Int>(0)
    val toplamFiyat: LiveData<Int> = _toplamFiyat

    fun sepetiYukle() {
        val liste = sepetRepo.sepetiGetir()
        _sepetListesi.value = liste
        toplamFiyatiHesapla(liste)
    }

    private fun toplamFiyatiHesapla(liste: List<Pair<Yemekler, Int>>) {
        val toplam = liste.sumOf { it.first.yemek_fiyat * it.second }
        _toplamFiyat.value = toplam
    }

    fun siparisiTamamla() {
        sepetRepo.sepetiTemizle()
        _sepetListesi.value = emptyList()
        _toplamFiyat.value = 0
    }
}
