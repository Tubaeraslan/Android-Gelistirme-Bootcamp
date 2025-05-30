package com.example.bitirmeprojesi.data.repo

import com.example.bitirmeprojesi.data.entity.Yemekler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SepetRepo @Inject constructor() {

    private val sepetListesi = mutableListOf<Pair<Yemekler, Int>>()

    fun sepeteEkle(yemek: Yemekler, adet: Int) {
        val mevcutIndex = sepetListesi.indexOfFirst { it.first.yemek_id == yemek.yemek_id }
        if (mevcutIndex >= 0) {
            val mevcut = sepetListesi[mevcutIndex]
            sepetListesi[mevcutIndex] = mevcut.copy(second = mevcut.second + adet)
        } else {
            sepetListesi.add(Pair(yemek, adet))
        }
    }

    fun sepetiGetir(): List<Pair<Yemekler, Int>> {
        return sepetListesi.toList()
    }

    fun sepetiTemizle() {
        sepetListesi.clear()
    }
}
