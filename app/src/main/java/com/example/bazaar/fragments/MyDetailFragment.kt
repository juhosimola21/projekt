package com.example.bazaar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bazaar.R
import com.example.bazaar.model.Product
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.ListViewModel
import com.example.bazaar.viewmodels.ListViewModelFactory


class MyDetailFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    //private lateinit var updateViewModel: UpdateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ListViewModelFactory(Repository())
        //val factory2 = UpdateViewModelFactory(Repository())
        viewModel = ViewModelProvider(requireActivity(), factory).get(ListViewModel::class.java)
        //updateViewModel = ViewModelProvider(requireActivity(),factory2).get(UpdateViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_detail, container, false)

        val productItem: Product = viewModel.getProduct()

        val seller: TextView = view.findViewById(R.id.sellerTextViewMyDetail)
        val date: TextView = view.findViewById(R.id.dateTextViewMy)
        val productName: TextView = view.findViewById(R.id.productNameTextViewMyDetail)
        val description: TextView = view.findViewById(R.id.descriptionTextViewMy)
        val active: TextView = view.findViewById(R.id.activeTextViewMy)
        val price: TextView = view.findViewById(R.id.priceTextViewMyDetail)
        val unit: TextView = view.findViewById(R.id.unitsTextViewMyDetail)
        val priceItem: TextView = view.findViewById(R.id.priceItemTextViewMyDetail)
        val button = view.findViewById<Button>(R.id.button2)

        seller.text = productItem.username
        date.text = productItem.creation_time.toString()
        productName.text = productItem.title
        description.text = productItem.description
        if(productItem.is_active){
            active.text = "Active"
        }
        else{
            active.text = "Inactive"
        }
        price.text = productItem.price_per_unit
        unit.text = productItem.units
        priceItem.text = productItem.price_per_unit

        button.setOnClickListener {
            //updateViewModel.product.value!!.product_id = productItem.product_id
            //findNavController().navigate(R.id.action_myCustomerDetailFragment_to_updateProductFragment)
        }
        return view
    }
}