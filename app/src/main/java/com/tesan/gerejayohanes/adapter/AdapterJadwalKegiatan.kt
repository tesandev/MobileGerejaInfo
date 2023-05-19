package com.tesan.gerejayohanes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tesan.gerejayohanes.databinding.ItmJadwalkegiatanBinding
import com.tesan.gerejayohanes.model.ResponseJadwalKegiatan
import kotlinx.android.synthetic.main.itm_jadwalkegiatan.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AdapterJadwalKegiatan(val list: ArrayList<ResponseJadwalKegiatan>):RecyclerView.Adapter<AdapterJadwalKegiatan.ViewHolder>() {
    inner class ViewHolder(itemview:ItmJadwalkegiatanBinding):RecyclerView.ViewHolder(itemview.root) {
        fun bind(bind: ResponseJadwalKegiatan){
            with(itemView){
                itemView.titleKegiatan.text = bind.namakegiatan
                itemView.lokasiKegiatan.text = bind.lokasi
                val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val date = LocalDate.parse(bind.tanggalkegiatan , firstApiFormat)
//                Log.d("parseTesting", date.dayOfWeek.toString()) // prints Wednesday
//                Log.d("parseTesting", date.month.toString()) // prints August
                itemView.waktuKegiatan.text = date.dayOfWeek.toString()+", "+bind.tanggalkegiatan+" "+bind.jamkegiatan
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        ItmJadwalkegiatanBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}