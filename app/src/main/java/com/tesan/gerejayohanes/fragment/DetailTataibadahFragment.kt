package com.tesan.gerejayohanes.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import com.tesan.gerejayohanes.R
import com.tesan.gerejayohanes.api.ApiConfig
import com.tesan.gerejayohanes.databinding.FragmentDetailTataibadahBinding
import com.tesan.gerejayohanes.model.ResponseTataibadah
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailTataibadahFragment : Fragment() {
    private lateinit var binding: FragmentDetailTataibadahBinding

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailTataibadahBinding.inflate(inflater, container, false)

        val args = arguments
        var tataibadahId:String? = null
        if (args != null) {
            tataibadahId = args.getString("tataibadah_id").toString()
        }

        Log.e("tbId",tataibadahId.toString())
        loaddetailTataIbadah(tataibadahId.toString())
        return binding.root
    }

    private fun loaddetailTataIbadah(tbId:String) {
        val loading = ProgressDialog(context)
        loading.setMessage("Loading...")
        loading.setCancelable(false)
        loading.show()

        ApiConfig.instance.tataibadahdetail(tbId).enqueue(object : Callback<ResponseTataibadah>{
            override fun onResponse(
                call: Call<ResponseTataibadah>,
                response: Response<ResponseTataibadah>
            ) {
                var rs = response.body()!!
                binding.titleDetailtataibadah.text = rs.namaibadah
                val htmlContent = rs.contentbody
                binding.bodyDetailtataibadah.text = HtmlCompat.fromHtml(htmlContent.toString(),HtmlCompat.FROM_HTML_MODE_COMPACT)
                loading.hide()
            }

            override fun onFailure(call: Call<ResponseTataibadah>, t: Throwable) {
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
            DetailTataibadahFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}