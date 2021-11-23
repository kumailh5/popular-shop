package com.kumail.popularshop.ui.itemdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kumail.popularshop.R
import com.kumail.popularshop.data.model.SaleItem
import com.kumail.popularshop.databinding.FragmentSaleDetailsBinding
import com.kumail.popularshop.util.setToolbarTitle
import com.kumail.popularshop.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * Created by kumailhussain on 15/10/2021.
 */
@AndroidEntryPoint
class SaleDetailsFragment : Fragment() {

    @Inject
    lateinit var adapter: ImagePagerAdapter

    private val args: SaleDetailsFragmentArgs by navArgs()
    private val viewModel: MainViewModel by activityViewModels()

    private var saleItem: SaleItem? = null

    private var _binding: FragmentSaleDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sale_details, container, false)
        this.setToolbarTitle(null)

        saleItem = viewModel.saleList.value?.find { it.id == args.saleItemId }
        setupDataBinding()
        setupViewPager(binding.vpImageCarousel)
        TabLayoutMediator(binding.tlIndicator, binding.vpImageCarousel) { _, _ -> }.attach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saleItem?.pictures?.let { adapter.setPictures(it) }
    }

    private fun setupDataBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.saleItem = saleItem
    }

    private fun setupViewPager(viewPager: ViewPager2) {
        viewPager.adapter = adapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                adapter.notifyDataSetChanged()
            }
        })
    }
}