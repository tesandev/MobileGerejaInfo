package com.tesan.gerejayohanes.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tesan.gerejayohanes.databinding.ItmRenunganBinding
import com.tesan.gerejayohanes.model.ResponseRenungan
import kotlinx.android.synthetic.main.itm_renungan.view.*

class AdapterRenungan(val list: ArrayList<ResponseRenungan>):RecyclerView.Adapter<AdapterRenungan.ViewHolder>() {

    private var onItemClickListener: AdapterRenungan.onAdapterListener? = null

    interface onAdapterListener{
        fun onClick(list:ResponseRenungan)
    }

    fun setOnItemClick(onItemCallback: onAdapterListener){
        this.onItemClickListener = onItemCallback
    }

    inner class ViewHolder(itemview : ItmRenunganBinding):RecyclerView.ViewHolder(itemview.root) {
        fun bind(bind : ResponseRenungan){
            with(itemView){
                itemView.jdlRenungan.text = bind.title
                itemView.bodyRenungan.text = Html.fromHtml(bind.body,Html.FROM_HTML_MODE_LEGACY)
                itemView.cardItemRenungan.setOnClickListener { onItemClickListener?.onClick(bind) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        ItmRenunganBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount()= list.size
}