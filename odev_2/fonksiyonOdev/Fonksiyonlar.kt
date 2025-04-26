package com.example.odev2.fonksiyonOdev

class Fonksiyonlar {
    fun get_fahrenhiet(celcius:Double):Double{
        val f = (celcius * 1.8) + 32
        return f
    }

    fun dikdortgen(i:Int,j:Int){
        val cevre = (i+j) * 2
        println(cevre)
    }

    fun faktoriyel(num:Int):Int{
        var i = 1;
        var sonuc = 1;
        while (i<=num){
            sonuc *= i
            i++
        }
        return sonuc
    }

    fun find_a(cumle:String):Int {
        var i = 0
        var result = 0
        while (i < cumle.length){
            if (cumle[i]=='a'){
                result++
            }
            i++
        }
        return result
    }

    fun ic_acilar(k:Int):Int{
        val sonuc = (k - 2) * 180
        return sonuc
    }

    fun maas(gun:Int):Int{
        val saat = gun * 8
        if (saat > 160){
            val saat_ucret = 160 *10
            val mesai_ucret = (saat - 160) * 20
            return (mesai_ucret + saat_ucret)
        }
        val ucret = saat * 10
        return ucret
    }

    fun ucret(kota:Int):Int{
        if (kota > 50) {
            val sonuc = ((kota -50) * 4) + 100
            return  sonuc
        }
        return 100
    }
}