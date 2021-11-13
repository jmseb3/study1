package com.wonddak.study1

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.wonddak.study1.API.upbitCoin
import com.wonddak.study1.API.upbitCoinItem
import com.wonddak.study1.databinding.CoinItemBinding

class CoinAdapter(
    val itemlist: upbitCoin
) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>(), Filterable {

    var unFilteredlist: ArrayList<upbitCoinItem>? = null
    var filteredList: ArrayList<upbitCoinItem>? = null

    init {
        this.filteredList = this.itemlist
        this.unFilteredlist = this.itemlist
    }

    inner class CoinViewHolder(binding: CoinItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val kor_name = binding.coinKorName
        val eng_name = binding.coinEngName
        val market = binding.market

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding =
            CoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.kor_name.text = filteredList!![position].korean_name
        holder.eng_name.text = filteredList!![position].english_name
        holder.market.text = filteredList!![position].market
    }

    override fun getItemCount(): Int {
        return filteredList!!.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                when {
                    charString.isEmpty() -> {
//                        검색할 단어가 비어있다면 원래 itemlist를 넣습니다.
                        filteredList = itemlist
                    }
                    else -> {
//                        검색할 단어가 있다면

                        val filteringList: ArrayList<upbitCoinItem> = ArrayList()
//                        필터링 되지 않은 목록을 돌아
                        for (name in unFilteredlist!!) {
//                            해당 단어의 조건에 만족하는 단어를 찾아
                            if ((name.korean_name.toLowerCase().contains(charString.toLowerCase()) or
                                        name.english_name.toLowerCase().contains(charString.toLowerCase()))
                            ) {
//                                필터링 리스트에 추가합니다.
                                filteringList.add(name)
                            }
                        }
                        filteredList = filteringList
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
//                필터결과를 반환하고
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
//                방금 필터링한 결과를 받아오고 적용시키고, 변화를 감지합니다.
                filteredList = results.values as ArrayList<upbitCoinItem>
                notifyDataSetChanged()
            }
        }

    }
}