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
import com.tesan.gerejayohanes.adapter.AdapterBacaan
import com.tesan.gerejayohanes.api.ApiConfig
import com.tesan.gerejayohanes.databinding.FragmentBacaanBinding
import com.tesan.gerejayohanes.model.ResponseBacaan
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BacaanFragment : Fragment() {

    private lateinit var binding: FragmentBacaanBinding
    private val list = ArrayList<ResponseBacaan>()

    // TODO: Rename and change types of parameters
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
        binding = FragmentBacaanBinding.inflate(inflater, container, false)

        val activity = requireActivity()
        activity.bottom_nav.selectedItemId = R.id.itm_none
        loadlist()
        return binding.root
    }

    private fun loadlist() {
        val loading = ProgressDialog(context)
        loading.setMessage("Loading...")
        loading.setCancelable(false)
        loading.show()

        binding.rvBacaan.setHasFixedSize(true)
        binding.rvBacaan.layoutManager = LinearLayoutManager(context)

        ApiConfig.instance.listbacaan().enqueue(object : Callback<ArrayList<ResponseBacaan>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseBacaan>>,
                response: Response<ArrayList<ResponseBacaan>>
            ) {
                val rs = response.body()!!
                list.clear()
                binding.rvBacaan.adapter?.notifyDataSetChanged()
                list.addAll(rs)
                val adp = AdapterBacaan(list)
                binding.rvBacaan.adapter = adp
                loading.hide()

                adp.setOnClick(object : AdapterBacaan.onAdapterListenBacaan{
                    override fun onClick(list: ResponseBacaan) {
                        moveFragment(DetailBacaanFragment(),list.id.toString())
                    }
                })
            }

            override fun onFailure(call: Call<ArrayList<ResponseBacaan>>, t: Throwable) {
                loading.hide()
                Log.e("ERR",t.message.toString())
                Toast.makeText(context,t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun moveFragment(fragment: Fragment,bacaan_id:String){
        val args = Bundle()
        val fragmentManager = parentFragmentManager
        args.putString("bacaan_id", bacaan_id)

        fragment.arguments = args
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out)
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BacaanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}