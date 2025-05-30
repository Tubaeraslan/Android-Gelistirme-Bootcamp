package com.example.bitirmeprojesi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.data.entity.Sepet_urun
import com.example.bitirmeprojesi.data.entity.Yemekler

class SepetAdapter(
    private var sepetList: List<Pair<Yemekler, Int>> = listOf()
) : RecyclerView.Adapter<SepetAdapter.SepetViewHolder>() {

    inner class SepetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivUrunResim: ImageView = itemView.findViewById(R.id.ivUrunResim)
        val tvUrunAdi: TextView = itemView.findViewById(R.id.tvUrunAdi)
        val tvUrunFiyat: TextView = itemView.findViewById(R.id.tvUrunFiyat)
        val tvUrunAdet: TextView = itemView.findViewById(R.id.tvUrunAdet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sepet_urun, parent, false)
        return SepetViewHolder(view)
    }

    override fun onBindViewHolder(holder: SepetViewHolder, position: Int) {
        val (yemek, adet) = sepetList[position]

        holder.tvUrunAdi.text = yemek.yemek_adi
        holder.tvUrunFiyat.text = "${yemek.yemek_fiyat} ₺"
        holder.tvUrunAdet.text = "Adet: $adet"

        Glide.with(holder.itemView.context)
            .load("http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}")
            .into(holder.ivUrunResim)
    }

    override fun getItemCount(): Int = sepetList.size

    fun updateList(newList: List<Pair<Yemekler, Int>>) {
        sepetList = newList
        notifyDataSetChanged()
    }
}

