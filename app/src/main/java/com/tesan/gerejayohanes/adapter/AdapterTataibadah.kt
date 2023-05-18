package com.tesan.gerejayohanes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tesan.gerejayohanes.databinding.ItmTataibadahBinding
import com.tesan.gerejayohanes.model.ResponseTataibadah
import kotlinx.android.synthetic.main.itm_tataibadah.view.*

class AdapterTataibadah(val list : ArrayList<ResponseTataibadah>):RecyclerView.Adapter<AdapterTataibadah.ViewHolder>() {

    private var onItemClickListener: onAdapterListener? = null

    inner class ViewHolder(itemview: ItmTataibadahBinding):RecyclerView.ViewHolder(itemview.root) {
        fun bind(bind:ResponseTataibadah){
            with(itemView){
                itemView.jdlTataibadah.text = bind.namaibadah
                itemView.cardItemTataibadah.setOnClickListener { onItemClickListener?.onClick(bind) }
            }
        }
    }

    interface onAdapterListener {
        fun onClick(list: ResponseTataibadah)
    }

    fun setOnItemClick(onItemClickCalback: onAdapterListener){
        this.onItemClickListener = onItemClickCalback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        ItmTataibadahBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}