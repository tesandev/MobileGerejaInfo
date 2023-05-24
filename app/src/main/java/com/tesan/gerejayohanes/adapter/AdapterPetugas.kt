package com.tesan.gerejayohanes.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tesan.gerejayohanes.databinding.ItmPetugasBinding
import com.tesan.gerejayohanes.model.ResponsePetugas
import kotlinx.android.synthetic.main.itm_petugas.view.bodyPetugas
import kotlinx.android.synthetic.main.itm_petugas.view.cardItemPetugas
import kotlinx.android.synthetic.main.itm_petugas.view.jdlPetugas
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class AdapterPetugas(val list: ArrayList<ResponsePetugas>):RecyclerView.Adapter<AdapterPetugas.ViewHolder>() {

    private var onClickListen : AdapterPetugas.onAdapterPetugasListenener? = null

    interface onAdapterPetugasListenener{
        fun onClick(list:ResponsePetugas)
    }

    fun setOnItemClick(onitemCallback:onAdapterPetugasListenener){
        this.onClickListen = onitemCallback
    }

    inner class ViewHolder(itemview:ItmPetugasBinding):RecyclerView.ViewHolder(itemview.root) {
        fun bind(bind:ResponsePetugas){
            with(itemView){
                val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val dateTemp = LocalDate.parse(bind.tanggalkegiatan , firstApiFormat)
                itemView.jdlPetugas.text = dateTemp.dayOfWeek.toString()+", "+dateTemp.dayOfMonth.toString()+" "+dateTemp.month+" "+dateTemp.year+", "+bind.jamkegiatan
                itemView.bodyPetugas.text = Html.fromHtml(bind.body,Html.FROM_HTML_MODE_LEGACY)
                itemView.cardItemPetugas.setOnClickListener { onClickListen?.onClick(bind) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        ItmPetugasBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}