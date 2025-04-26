package com.example.odev2.fonksiyonOdev

fun main() {
    val f = Fonksiyonlar()

    val sonuc = f.get_fahrenhiet(32.0)
    println(sonuc)

    f.dikdortgen(3,4)

    val sonuc2 =f.faktoriyel(5)
    println(sonuc2)

    val cumle = "aaaatuaba"
    val sonuc3 = f.find_a(cumle)
    println(sonuc3)

    val sonuc4 = f.ic_acilar(4)
    println(sonuc4)

    val sonuc5 = f.maas(25)
    println(sonuc5)

    val sonuc6 = f.ucret(70)
    println(sonuc6)
}