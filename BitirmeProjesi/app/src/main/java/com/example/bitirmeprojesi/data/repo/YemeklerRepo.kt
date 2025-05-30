package com.example.bitirmeprojesi.data.repo

import javax.inject.Inject
import com.example.bitirmeprojesi.data.entity.Yemekler

class YemeklerRepo @Inject constructor() {

    private val yemekler = listOf(
        Yemekler(1, "Pizza", "pizza.jpg", 50),
        Yemekler(2, "Burger", "burger.jpg", 30),
        Yemekler(3, "Lahmacun", "lahmacun.jpg", 25),
        Yemekler(4, "Kebap", "kebap.jpg", 45)
    )

    fun tumYemekleriGetir(): List<Yemekler> {
        return yemekler
    }

    fun yemekAra(arananKelime: String): List<Yemekler> {
        return yemekler.filter {
            it.yemek_adi.contains(arananKelime, ignoreCase = true)
        }
    }
}
