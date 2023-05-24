package com.tesan.gerejayohanes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tesan.gerejayohanes.R
import com.tesan.gerejayohanes.api.ApiConfig
import com.tesan.gerejayohanes.databinding.ItmBacaanBinding
import com.tesan.gerejayohanes.model.ResponseBacaan
import kotlinx.android.synthetic.main.itm_bacaan.view.cardItemBacaan
import kotlinx.android.synthetic.main.itm_bacaan.view.imgBacaan
import kotlinx.android.synthetic.main.itm_bacaan.view.jdlBacaan

class AdapterBacaan(val list:ArrayList<ResponseBacaan>):RecyclerView.Adapter<AdapterBacaan.ViewHolder>() {

    private var onItemClickListenBacaan : onAdapterListenBacaan? = null

    interface onAdapterListenBacaan{
        fun onClick(list:ResponseBacaan)
    }

    fun setOnClick(onItemClickCallback : onAdapterListenBacaan){
        this.onItemClickListenBacaan = onItemClickCallback
    }

    inner class ViewHolder(itemview:ItmBacaanBinding):RecyclerView.ViewHolder(itemview.root) {
        fun bind(bind : ResponseBacaan){
            with(itemView){
                Glide.with(context)
                    .load(ApiConfig.mediaUrl+bind.featuredImage)
                    .placeholder(R.drawable.noimage)
                    .into(itemView.imgBacaan)
                itemView.jdlBacaan.text = bind.title
                itemView.cardItemBacaan.setOnClickListener { onItemClickListenBacaan?.onClick(bind) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        ItmBacaanBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}