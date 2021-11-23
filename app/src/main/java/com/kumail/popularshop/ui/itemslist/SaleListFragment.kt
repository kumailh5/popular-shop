package com.kumail.popularshop.ui.itemslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kumail.popularshop.R
import com.kumail.popularshop.databinding.FragmentSaleListBinding
import com.kumail.popularshop.util.navigateTo
import com.kumail.popularshop.util.setToolbarTitle
import com.kumail.popularshop.util.showErrorDialog
import com.kumail.popularshop.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by kumailhussain on 15/10/2021.
 */
@AndroidEntryPoint
class SaleListFragment : Fragment() {

    @Inject
    lateinit var adapter: SaleListAdapter

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentSaleListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sale_list, container, false)
        this.setToolbarTitle(getString(R.string.popular_items))

        setupDataBinding()
        setupSaleList(binding.rvSale)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupDataBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun setupObservers() {
        viewModel.errorMessage.observe(viewLifecycleOwner, { errorRes ->
            showErrorDialog(requireContext(), errorRes)
        })

        viewModel.saleList.observe(viewLifecycleOwner, {
            adapter.setSaleList(it)
        })
    }

    private fun setupSaleList(listView: RecyclerView) {
        adapter.setOnItemClickListener { saleItemId -> navigateToSaleDetails(saleItemId) }
        listView.layoutManager = LinearLayoutManager(requireContext())
        listView.adapter = adapter
        listView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    private fun navigateToSaleDetails(saleItemId: Int) {
        this.navigateTo(SaleListFragmentDirections.actionSaleListToSaleDetails(saleItemId))
    }
}