package com.wonddak.study1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.wonddak.study1.API.upBitClient
import com.wonddak.study1.API.upbitCoin
import com.wonddak.study1.databinding.ActivityCoinInfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinInfo : AppCompatActivity() {
    private lateinit var binding: ActivityCoinInfoBinding
    private var adapter: CoinAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinInfoBinding.inflate(layoutInflater)
        upBitClient.api.getlist().enqueue(object : Callback<upbitCoin>{
            override fun onResponse(call: Call<upbitCoin>, response: Response<upbitCoin>) {
//               성공시 할 행동
                if (response.isSuccessful) {
                    adapter = CoinAdapter(response.body()!!)
                    binding.coinList.adapter = adapter

                }
            }

            override fun onFailure(call: Call<upbitCoin>, t: Throwable) {
//              실패시 할 행동
                Toast.makeText(this@CoinInfo,"실패",Toast.LENGTH_SHORT).show()
            }
        })

        binding.searchBox.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter!!.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        setContentView(binding.root)
    }
}