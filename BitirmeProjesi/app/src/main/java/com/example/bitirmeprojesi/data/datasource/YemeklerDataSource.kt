package com.example.yemekleruygulamasi.data.datasource

import android.util.Log
import com.example.bitirmeprojesi.data.entity.CRUDCevap
import com.example.bitirmeprojesi.data.entity.Sepet_urun
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource(private val yemeklerDao: YemeklerDao) {

    private val kullaniciAdi = "tubaeraslan"

    suspend fun yemekleriYukle(): List<Yemekler> = withContext(Dispatchers.IO) {
        return@withContext yemeklerDao.yemekleriYukle().yemekler
    }

    suspend fun sepeteEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int
    ) {
        val cevap: CRUDCevap = yemeklerDao.sepeteEkle(
            yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullaniciAdi
        )
        Log.e("Sepete Ekle", "Success: ${cevap.success} - Message: ${cevap.message}")
    }

    suspend fun sepettekileriYukle(): List<Sepet_urun> = withContext(Dispatchers.IO) {
        return@withContext yemeklerDao.sepettekileriYukle(kullaniciAdi).sepet_urun
    }

    suspend fun sepettenSil(sepet_yemek_id: Int) {
        val cevap: CRUDCevap = yemeklerDao.sepettenSil(sepet_yemek_id, kullaniciAdi)
        Log.e("Sepetten Sil", "Success: ${cevap.success} - Message: ${cevap.message}")
    }
}
