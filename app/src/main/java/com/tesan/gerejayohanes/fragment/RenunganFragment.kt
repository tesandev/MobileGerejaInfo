package com.tesan.gerejayohanes.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tesan.gerejayohanes.R
import com.tesan.gerejayohanes.adapter.AdapterRenungan
import com.tesan.gerejayohanes.api.ApiConfig
import com.tesan.gerejayohanes.databinding.FragmentRenunganBinding
import com.tesan.gerejayohanes.model.ResponseRenungan
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.itm_renungan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RenunganFragment : Fragment() {

    private lateinit var binding: FragmentRenunganBinding
    private val list = ArrayList<ResponseRenungan>()

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
        binding = FragmentRenunganBinding.inflate(inflater, container, false)

        val activity = requireActivity()
        activity.bottom_nav.selectedItemId = R.id.itm_none
        renunganlist()
        return binding.root
    }

    private fun renunganlist() {
        val loading = ProgressDialog(context)
        loading.setMessage("Loading...")
        loading.setCancelable(false)
        loading.show()

        binding.rvRenungan.setHasFixedSize(true)
        binding.rvRenungan.layoutManager = LinearLayoutManager(context)

        ApiConfig.instance.listrenungan().enqueue(object : Callback<ArrayList<ResponseRenungan>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseRenungan>>,
                response: Response<ArrayList<ResponseRenungan>>
            ) {
                val rs = response.body()!!
                list.clear()
                binding.rvRenungan.adapter?.notifyDataSetChanged()

                list.addAll(rs)
                val adp = AdapterRenungan(list)
                binding.rvRenungan.adapter = adp
                loading.hide()
                adp.setOnItemClick(object : AdapterRenungan.onAdapterListener{
                    override fun onClick(list: ResponseRenungan) {
                        if(contentBody.isVisible == false){
                            TransitionManager.beginDelayedTransition(cardItemRenungan, AutoTransition())
                            contentBody.setVisibility(View.VISIBLE)
                            more.setImageResource(R.drawable.ic_baseline_expand_less_24)
                        }else{
                            TransitionManager.beginDelayedTransition(cardItemRenungan, AutoTransition())
                            contentBody.setVisibility(View.GONE)
                            more.setImageResource(R.drawable.ic_baseline_expand_more_24)
                        }
                    }

                })
            }

            override fun onFailure(call: Call<ArrayList<ResponseRenungan>>, t: Throwable) {
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
            RenunganFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}