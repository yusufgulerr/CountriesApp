package com.yusufguler.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusufguler.countries.R
import com.yusufguler.countries.databinding.ItemCountryBinding
import com.yusufguler.countries.model.Country

class CountryAdapter(val countryList:ArrayList<Country>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(){


    class CountryViewHolder(var view: View) :RecyclerView.ViewHolder(view){
        val binding = ItemCountryBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
            holder.binding.name.text = countryList[position].countryName
            holder.binding.region.text =countryList[position].countryRegion
    }
    fun updateCountryList(newCountryList:ArrayList<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}