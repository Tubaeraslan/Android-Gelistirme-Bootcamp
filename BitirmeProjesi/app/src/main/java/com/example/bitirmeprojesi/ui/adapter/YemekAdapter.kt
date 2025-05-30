package com.example.bitirmeprojesi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.data.entity.Yemekler

class YemekAdapter(
    private var yemekList: List<Yemekler>
) : RecyclerView.Adapter<YemekAdapter.YemekViewHolder>() {

    class YemekViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivYemekResim: ImageView = itemView.findViewById(R.id.ivYemekResim)
        val tvYemekAdi: TextView = itemView.findViewById(R.id.tvYemekAdi)
        val tvYemekFiyat: TextView = itemView.findViewById(R.id.tvYemekFiyat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_yemek, parent, false)
        return YemekViewHolder(view)
    }

    override fun onBindViewHolder(holder: YemekViewHolder, position: Int) {
        val yemek = yemekList[position]
        holder.tvYemekAdi.text = yemek.yemek_adi
        holder.tvYemekFiyat.text = "${yemek.yemek_fiyat} ₺"

        Glide.with(holder.itemView.context)
            .load("http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}")
            .into(holder.ivYemekResim)
    }

    override fun getItemCount(): Int = yemekList.size

    fun updateList(newList: List<Yemekler>) {
        yemekList = newList
        notifyDataSetChanged()
    }
}

