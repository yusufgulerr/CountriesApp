package com.yusufguler.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.yusufguler.countries.R
import com.yusufguler.countries.databinding.ItemCountryBinding
import com.yusufguler.countries.model.Country
import com.yusufguler.countries.util.downloadFromURL
import com.yusufguler.countries.util.placeHolderProgressBar
import com.yusufguler.countries.view.FeedFragmentDirections

class CountryAdapter(val countryList:ArrayList<Country>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener{


    class CountryViewHolder(var view: ItemCountryBinding) :RecyclerView.ViewHolder(view.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(inflater,R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this
    }
    fun updateCountryList(newCountryList:ArrayList<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val uuid = ItemCountryBinding.bind(v).invisibleUuid.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment()
        action.countryUuid = uuid
        Navigation.findNavController(v).navigate(action)

    }

}