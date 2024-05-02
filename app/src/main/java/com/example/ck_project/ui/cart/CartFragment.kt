package com.example.ck_project.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ck_project.databinding.FragmentCartBinding
import com.example.ck_project.ui.cart.dialog.OrderDialog
import com.example.ck_project.ui.home.ProductsRecyclerAdapter

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.readyProducts.observe(viewLifecycleOwner, Observer {
            val recycler = binding.cartRecycler
            val adapter = CartRecyclerAdapter(viewModel, it)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
            if (it.size > 0) {
                binding.orderButton.visibility = View.VISIBLE
            } else {
                binding.orderButton.visibility = View.GONE
            }
        })

        binding.orderButton.setOnClickListener {
            val dialog = OrderDialog()
            dialog.show(parentFragmentManager, "order")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }
}