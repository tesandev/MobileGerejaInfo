package com.tesan.gerejayohanes.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.tesan.gerejayohanes.R
import com.tesan.gerejayohanes.api.ApiConfig
import com.tesan.gerejayohanes.databinding.FragmentDetailBacaanBinding
import com.tesan.gerejayohanes.model.ResponseBacaan
import kotlinx.android.synthetic.main.itm_bacaan.view.imgBacaan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailBacaanFragment : Fragment() {
    private lateinit var binding: FragmentDetailBacaanBinding

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentDetailBacaanBinding.inflate(inflater, container, false)
        val args = arguments
        var bacaanId:String? = null
        if (args != null) {
            bacaanId = args.getString("bacaan_id").toString()
            loaddetail(bacaanId)
        }
        return binding.root
    }

    private fun loaddetail(bacaanId:String) {
        val loading = ProgressDialog(context)
        loading.setMessage("Loading...")
        loading.setCancelable(false)
        loading.show()

        ApiConfig.instance.bacaandetail(bacaanId).enqueue(object : Callback<ResponseBacaan>{
            override fun onResponse(
                call: Call<ResponseBacaan>,
                response: Response<ResponseBacaan>
            ) {
                val rs = response.body()!!
                binding.detailJdlBacaan.text = rs.title
                context?.let {
                    Glide.with(it)
                        .load(ApiConfig.mediaUrl+rs.featuredImage)
                        .placeholder(R.drawable.noimage)
                        .into(binding.imgBacaanDetail)
                }
                val contentOut = rs.content
                binding.contentBacaan.text = HtmlCompat.fromHtml(contentOut.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT)
                loading.hide()
            }

            override fun onFailure(call: Call<ResponseBacaan>, t: Throwable) {
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
            DetailBacaanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}