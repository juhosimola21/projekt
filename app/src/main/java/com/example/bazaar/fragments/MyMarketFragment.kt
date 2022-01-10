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
import com.example.bazaar.R
import com.example.bazaar.adapters.DataAdapter
import com.example.bazaar.model.Product
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.ListViewModel
import com.example.bazaar.viewmodels.ListViewModelFactory
import com.example.bazaar.viewmodels.MyMarketViewModel
import com.example.bazaar.viewmodels.MyMarketViewModelFactory


class MyMarketFragment : Fragment(), DataAdapter.OnItemClickListener, DataAdapter.OnItemLongClickListener  {
    lateinit var mymarketViewModel: MyMarketViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = MyMarketViewModelFactory(Repository())
        mymarketViewModel = ViewModelProvider(requireActivity(), factory).get(MyMarketViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list3, container, false)
        recycler_view = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        mymarketViewModel.products.observe(viewLifecycleOwner){
            adapter.setData(mymarketViewModel.products.value as ArrayList<Product>)
            adapter.notifyDataSetChanged()
        }
        return view
    }

    private fun setupRecyclerView(){
        adapter = DataAdapter(ArrayList<Product>(), this.requireContext(), this, this)
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
        val product = mymarketViewModel.myproducts[position]
        mymarketViewModel.currentPosition = mymarketViewModel.products.value!!.indexOf(product)
        findNavController().navigate(R.id.action_myMarketFragment_to_myDetailFragment)
        Log.d("Adapter", "AdapterPosition: $position")
    }

    override fun onItemLongClick(position: Int) {
//        TODO("Not yet implemented")
    }
}