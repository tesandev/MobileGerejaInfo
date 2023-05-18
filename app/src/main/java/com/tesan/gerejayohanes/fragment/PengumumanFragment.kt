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
import com.tesan.gerejayohanes.adapter.AdapterPengumuman
import com.tesan.gerejayohanes.api.ApiConfig
import com.tesan.gerejayohanes.databinding.FragmentPengumumanBinding
import com.tesan.gerejayohanes.model.ResponsePengumuman
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PengumumanFragment : Fragment() {

    private lateinit var binding: FragmentPengumumanBinding

    private val list = ArrayList<ResponsePengumuman>()

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
        binding = FragmentPengumumanBinding.inflate(inflater, container, false)
        loadlist()
        return binding.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PengumumanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun loadlist() {
        val loading = ProgressDialog(context)
        loading.setMessage("Loading...")
        loading.setCancelable(false)
        loading.show()

        binding.rvPengumuman.setHasFixedSize(true)
        binding.rvPengumuman.layoutManager = LinearLayoutManager(context)

        ApiConfig.instance.listpengumuman().enqueue(object : Callback<ArrayList<ResponsePengumuman>>{
            override fun onResponse(
                call: Call<ArrayList<ResponsePengumuman>>,
                response: Response<ArrayList<ResponsePengumuman>>
            ) {
                var listresponse = response.body()!!
                list.clear()
                binding.rvPengumuman.adapter?.notifyDataSetChanged()

                list.addAll(listresponse)
                val adp = AdapterPengumuman(list)
                binding.rvPengumuman.adapter = adp
                loading.hide()
            }

            override fun onFailure(call: Call<ArrayList<ResponsePengumuman>>, t: Throwable) {
                loading.hide()
                Log.e("ERR",t.message.toString())
                Toast.makeText(context,t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}