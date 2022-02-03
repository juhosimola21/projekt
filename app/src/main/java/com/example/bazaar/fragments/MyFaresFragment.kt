package com.example.bazaar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar.MyApplication
import com.example.bazaar.R
import com.example.bazaar.adapters.MyMarketDataAdapter
import com.example.bazaar.model.Product
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.ListViewModel
import com.example.bazaar.viewmodels.ListViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MyFaresFragment : Fragment(), MyMarketDataAdapter.OnItemClickListener {
    lateinit var myMarketViewModel: ListViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: MyMarketDataAdapter
    private var myProducts: ArrayList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ListViewModelFactory(Repository())
        myMarketViewModel = ViewModelProvider(requireActivity(), factory).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_my_fares, container, false)
        recycler_view = view.findViewById(R.id.recycler_view_orders)
        setupRecyclerView()
        myMarketViewModel.getProducts()
        myMarketViewModel.products.observe(viewLifecycleOwner){
            myProducts = myMarketViewModel.products.value!!.filter{
                it.order.equals("ordered")
            } as ArrayList<Product>
            adapter.setData(myProducts)
            adapter.notifyDataSetChanged()
        }

        return view
    }

    private fun setupRecyclerView(){
        adapter = MyMarketDataAdapter(ArrayList<Product>(), this, myMarketViewModel)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context)
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        recycler_view.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
        val product = myProducts[position]
        myMarketViewModel.currentPosition = myMarketViewModel.products.value!!.indexOf(product)
        //findNavController().navigate(R.id.action_myFaresFragment_to_myDetailFragment)
        Log.d("Adapter", "AdapterPosition: $position")
    }
}