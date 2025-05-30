package com.example.bitirmeprojesi.ui.fragment

import androidx.lifecycle.*
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.repo.YemeklerRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(
    private val yemekRepo: YemeklerRepo
) : ViewModel() {

    sealed class Resource<T>(
        val data: T? = null,
        val message: String? = null
    ) {
        class Loading<T>: Resource<T>()
        class Success<T>(data: T): Resource<T>(data)
        class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    }

    private val _yemekDurumu = MutableLiveData<Resource<List<Yemekler>>>()
    val yemekDurumu: LiveData<Resource<List<Yemekler>>> = _yemekDurumu

    fun yemekleriYukle() {
        _yemekDurumu.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val yemekler = yemekRepo.tumYemekleriGetir()
                _yemekDurumu.value = Resource.Success(yemekler)
            } catch (e: Exception) {
                _yemekDurumu.value = Resource.Error("Yemekler yüklenirken hata oluştu: ${e.localizedMessage}")
            }
        }
    }

    fun ara(arananKelime: String) {
        _yemekDurumu.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val bulunanlar = yemekRepo.yemekAra(arananKelime)
                _yemekDurumu.value = Resource.Success(bulunanlar)
            } catch (e: Exception) {
                _yemekDurumu.value = Resource.Error("Arama yapılırken hata oluştu: ${e.localizedMessage}")
            }
        }
    }
}
