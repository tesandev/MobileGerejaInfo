package com.tesan.gerejayohanes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tesan.gerejayohanes.databinding.ItmPengumumanBinding
import com.tesan.gerejayohanes.model.ResponsePengumuman
import kotlinx.android.synthetic.main.itm_pengumuman.view.*

class AdapterPengumuman(val list:List<ResponsePengumuman>):RecyclerView.Adapter<AdapterPengumuman.ViewHolder>() {
    class ViewHolder(itemview: ItmPengumumanBinding):RecyclerView.ViewHolder(itemview.root) {
        fun bind(bind: ResponsePengumuman){
            with(itemView){
                itemView.jdlPengumuman.text = bind.title
                itemView.body.text = bind.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        ItmPengumumanBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}