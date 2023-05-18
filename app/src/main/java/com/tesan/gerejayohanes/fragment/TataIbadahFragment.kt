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
import com.tesan.gerejayohanes.adapter.AdapterPengumuman
import com.tesan.gerejayohanes.adapter.AdapterTataibadah
import com.tesan.gerejayohanes.api.ApiConfig
import com.tesan.gerejayohanes.databinding.FragmentTataIbadahBinding
import com.tesan.gerejayohanes.model.ResponsePengumuman
import com.tesan.gerejayohanes.model.ResponseTataibadah
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val tataibadah_id = "tataibadah_id"

class TataIbadahFragment : Fragment() {

    private lateinit var binding: FragmentTataIbadahBinding
    private val list = ArrayList<ResponseTataibadah>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTataIbadahBinding.inflate(inflater, container, false)

        val activity = requireActivity()
        activity.bottom_nav.selectedItemId = R.id.itm_none

        loadlist()

        return binding.root
    }



    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            TataIbadahFragment().apply {
                arguments = Bundle().apply {
                    putString(tataibadah_id, param1)
                }
            }
    }

    private fun loadlist() {
        val loading = ProgressDialog(context)
        loading.setMessage("Loading...")
        loading.setCancelable(false)
        loading.show()

        binding.rvTataibadah.setHasFixedSize(true)
        binding.rvTataibadah.layoutManager = LinearLayoutManager(context)

        ApiConfig.instance.listtataibadah().enqueue(object :
            Callback<ArrayList<ResponseTataibadah>> {
            override fun onResponse(
                call: Call<ArrayList<ResponseTataibadah>>,
                response: Response<ArrayList<ResponseTataibadah>>
            ) {
                var listresponse = response.body()!!
                list.clear()
                binding.rvTataibadah.adapter?.notifyDataSetChanged()

                list.addAll(listresponse)
                val adp = AdapterTataibadah(list)
                binding.rvTataibadah.adapter = adp
                loading.hide()

                adp.setOnItemClick(object : AdapterTataibadah.onAdapterListener{
                    override fun onClick(list: ResponseTataibadah) {
                        moveFragment(DetailTataibadahFragment(),list.id.toString())
                    }

                })
            }

            override fun onFailure(call: Call<ArrayList<ResponseTataibadah>>, t: Throwable) {
                loading.hide()
                Log.e("ERR",t.message.toString())
                Toast.makeText(context,t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun moveFragment(fragment: Fragment,tatabadah_id:String){
        val args = Bundle()
        val fragmentManager = parentFragmentManager
        args.putString("tataibadah_id", tatabadah_id)

        fragment.arguments = args
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out)
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}