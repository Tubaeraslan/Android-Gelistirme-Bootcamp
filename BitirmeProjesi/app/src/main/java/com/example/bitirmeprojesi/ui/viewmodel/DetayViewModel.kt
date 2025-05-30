package com.example.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.repo.SepetRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetayViewModel @Inject constructor(
    private val sepetRepo: SepetRepo
) : ViewModel() {

    fun sepeteEkle(yemek: Yemekler, adet: Int) {
        viewModelScope.launch {
            sepetRepo.sepeteEkle(yemek, adet)
        }
    }
}
