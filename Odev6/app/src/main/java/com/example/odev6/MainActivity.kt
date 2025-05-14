package com.example.odev6

import NewsAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsList: List<News>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.newsRV)
        recyclerView.layoutManager = LinearLayoutManager(this)

        newsList = listOf(
            News("Yeni Teknoloji Atılımı", "Yapay zeka günlük hayatımıza daha da entegre oluyo",
                "https://loremflickr.com/640/360/technology", "14 Mayıs 2025"),
            News("Spor Haberleri", "Fenerbahçe bu hafta da galibiyet aldı!",
                "https://loremflickr.com/640/360/soccer", "13 Mayıs 2025"),
            News("Sağlık Haberleri", "Covid-19 aşısı yeni mutasyonlara karşı etkili bulundu.",
                "https://loremflickr.com/640/360/health", "12 Mayıs 2025"),
            News("Ekonomi", "Dolar kuru yükselmeye devam ediyor.",
                "https://loremflickr.com/640/360/economy", "11 Mayıs 2025"),
            News("Kültür & Sanat", "Ünlü sanatçı yeni albümünü piyasaya sürdü.",
                "https://loremflickr.com/640/360/art", "10 Mayıs 2025"),
            News("Dünya Haberleri", "Yeni Zelanda'da büyük bir deprem meydana geldi.",
                "https://loremflickr.com/640/360/world", "9 Mayıs 2025"),
            News("Teknoloji", "Apple yeni iPhone modelini tanıttı.",
                "https://loremflickr.com/640/360/iphone", "8 Mayıs 2025"),
            News("Spor", "NBA final serisi başladı, heyecan dorukta!",
                "https://loremflickr.com/640/360/basketball", "7 Mayıs 2025"),
            News("Bilim", "Mars'a yeni bir uzay aracı gönderildi.",
                "https://loremflickr.com/640/360/science", "6 Mayıs 2025"),
            News("Moda", "2025 yaz modası, canlı renklerle dikkat çekiyor.",
                "https://loremflickr.com/640/360/fashion", "5 Mayıs 2025")
        )

        recyclerView.adapter = NewsAdapter(newsList)
    }
}