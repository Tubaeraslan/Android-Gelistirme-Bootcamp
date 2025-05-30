package com.example.bitirmeprojesi.retrofit

class ApiUtils {
    companion object {
        // http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php
        // http://kasimadalan.pe.hu/ -> BASE URL
        // yemekler/tumYemekleriGetir.php -> API URL
        private const val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getYemeklerDao(): YemeklerDao {
            return RetrofitClient.getClient(BASE_URL).create(YemeklerDao::class.java)
        }
    }
}
