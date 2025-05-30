package com.example.bitirmeprojesi.data.entity

import com.example.bitirmeprojesi.data.entity.Yemekler

data class YemeklerCevap(
    val yemekler: List<Yemekler>,
    val success: Int
)
