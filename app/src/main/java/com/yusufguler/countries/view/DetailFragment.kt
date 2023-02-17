package com.yusufguler.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.yusufguler.countries.view.DetailFragmentArgs
import com.yusufguler.countries.R
import com.yusufguler.countries.databinding.FragmentDetailBinding
import com.yusufguler.countries.databinding.FragmentFeedBinding
import com.yusufguler.countries.util.downloadFromURL
import com.yusufguler.countries.util.placeHolderProgressBar
import com.yusufguler.countries.viewmodel.CountryViewModel


class DetailFragment : Fragment() {

    private lateinit var viewModel:CountryViewModel

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var countryUuid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            countryUuid = DetailFragmentArgs.fromBundle(it).countryUuid
        }
        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner,Observer{country->
            country?.let{
                binding.countryName.text = country.countryName
                binding.countryCapital.text = country.countryCapital
                binding.countryCurrency.text = country.countryCurrency
                binding.countryLanguage.text = country.countryLanguage
                binding.countryRegion.text = country.countryRegion
                context?.let {
                    binding.countryImage.downloadFromURL(country.imgeUrl, placeHolderProgressBar(it))
                }
            }

        })
    }


}