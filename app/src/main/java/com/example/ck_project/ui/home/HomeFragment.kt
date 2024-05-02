package com.example.ck_project.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ck_project.databinding.FragmentHomeBinding
import com.example.ck_project.ui.cart.CartViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        cartViewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = binding.productsRecycler
        val adapter = ProductsRecyclerAdapter(cartViewModel)

        recycler.adapter = adapter


        viewModel.stateLiveData.observe(viewLifecycleOwner, Observer { state ->
            val isSuccess = !state.isLoading && state.errorMessage == null && state.items != null
            if (isSuccess) {
                adapter.updateData(state.items!!)
            } else {
                state.errorMessage?.let { Log.e("RRR", it) }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}