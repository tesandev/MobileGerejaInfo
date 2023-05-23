package com.tesan.gerejayohanes.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.tesan.gerejayohanes.R
import com.tesan.gerejayohanes.adapter.AdapterPetugas
import com.tesan.gerejayohanes.api.ApiConfig
import com.tesan.gerejayohanes.databinding.FragmentPetugasBinding
import com.tesan.gerejayohanes.model.ResponsePetugas
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.itm_petugas.cardItemPetugas
import kotlinx.android.synthetic.main.itm_petugas.groupBodyPetugas
import kotlinx.android.synthetic.main.itm_petugas.morePetugas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PetugasFragment : Fragment() {

    private lateinit var binding:FragmentPetugasBinding

    private var param1: String? = null
    private var param2: String? = null

    private val list = ArrayList<ResponsePetugas>()

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
        binding = FragmentPetugasBinding.inflate(inflater, container, false)

        val activity = requireActivity()
        activity.bottom_nav.selectedItemId = R.id.itm_none

        listpetugas()

        return binding.root
    }

    private fun listpetugas() {
        val loading = ProgressDialog(context)
        loading.setMessage("Loading...")
        loading.setCancelable(false)
        loading.show()

        binding.rvPetugas.setHasFixedSize(true)
        binding.rvPetugas.layoutManager = LinearLayoutManager(context)

        ApiConfig.instance.listpetugas().enqueue(object:Callback<ArrayList<ResponsePetugas>>{
            override fun onResponse(
                call: Call<ArrayList<ResponsePetugas>>,
                response: Response<ArrayList<ResponsePetugas>>
            ) {
                val rs = response.body()!!
                list.clear()
                binding.rvPetugas.adapter?.notifyDataSetChanged()
                list.addAll(rs)
                val adp = AdapterPetugas(list)
                binding.rvPetugas.adapter = adp
                loading.hide()

                adp.setOnItemClick(object:AdapterPetugas.onAdapterPetugasListenener{
                    override fun onClick(list: ResponsePetugas) {
                        if (groupBodyPetugas.isVisible == false){
                            TransitionManager.beginDelayedTransition(cardItemPetugas, AutoTransition())
                            groupBodyPetugas.setVisibility(View.VISIBLE)
                            morePetugas.setImageResource(R.drawable.ic_baseline_expand_less_24)
                        }else{
                            TransitionManager.beginDelayedTransition(cardItemPetugas, AutoTransition())
                            groupBodyPetugas.setVisibility(View.GONE)
                            morePetugas.setImageResource(R.drawable.ic_baseline_expand_more_24)
                        }
                    }

                })
            }

            override fun onFailure(call: Call<ArrayList<ResponsePetugas>>, t: Throwable) {
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
            PetugasFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}