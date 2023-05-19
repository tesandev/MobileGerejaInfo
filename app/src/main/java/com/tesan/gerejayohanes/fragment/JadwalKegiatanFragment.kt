package com.tesan.gerejayohanes.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tesan.gerejayohanes.R
import com.tesan.gerejayohanes.adapter.AdapterJadwalKegiatan
import com.tesan.gerejayohanes.api.ApiConfig
import com.tesan.gerejayohanes.databinding.FragmentJadwalKegiatanBinding
import com.tesan.gerejayohanes.model.ResponseJadwalKegiatan
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class JadwalKegiatanFragment : Fragment() {

    private lateinit var binding:FragmentJadwalKegiatanBinding
    private val list = ArrayList<ResponseJadwalKegiatan>()
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJadwalKegiatanBinding.inflate(inflater, container, false)

        val activity = requireActivity()
        activity.bottom_nav.selectedItemId = R.id.itm_none

        loadlistjdwakegiatan()

        return binding.root
    }

    private fun loadlistjdwakegiatan() {
        val loading = ProgressDialog(context)
        loading.setMessage("Loading...")
        loading.setCancelable(false)
        loading.show()

        binding.rvJadwalkegiatan.setHasFixedSize(true)
        binding.rvJadwalkegiatan.layoutManager = LinearLayoutManager(context)

        ApiConfig.instance.listjadwalkegiatan().enqueue(object : Callback<ArrayList<ResponseJadwalKegiatan>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseJadwalKegiatan>>,
                response: Response<ArrayList<ResponseJadwalKegiatan>>
            ) {
                val rs = response.body()!!
                list.clear()
                binding.rvJadwalkegiatan.adapter?.notifyDataSetChanged()

                list.addAll(rs)
                val adp = AdapterJadwalKegiatan(list)
                binding.rvJadwalkegiatan.adapter = adp
                loading.hide()
            }

            override fun onFailure(call: Call<ArrayList<ResponseJadwalKegiatan>>, t: Throwable) {
                loading.hide()
                Log.e("ERR",t.message.toString())
                Toast.makeText(context,t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JadwalKegiatanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}