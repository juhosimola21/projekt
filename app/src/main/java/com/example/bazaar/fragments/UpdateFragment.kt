package com.example.bazaar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bazaar.R
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.UpdateViewModel
import com.example.bazaar.viewmodels.UpdateViewModelFactory


class UpdateFragment : Fragment() {
    private lateinit var updateViewModel: UpdateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = UpdateViewModelFactory(Repository())
        updateViewModel =
            ViewModelProvider(requireActivity(), factory).get(UpdateViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_update, container, false)

        val title = view.findViewById<EditText>(R.id.editTextTitleUpdate)
        val price  = view.findViewById<EditText>(R.id.editTextPriceUpdate)
        val isActive = view.findViewById<Switch>(R.id.switch1)
        val units = view.findViewById<EditText>(R.id.editTextAmountUpdate)
        val description = view.findViewById<EditText>(R.id.editTextDescriptionUpdate)

        val button = view.findViewById<Button>(R.id.finishUpdateBtn)
        button.setOnClickListener {
            updateViewModel.product.value.let {
                if (it != null) {
                    it.title = title.text.toString()
                }
                if (it != null) {
                    it.price_per_unit = price.text.toString()
                }
                if (it != null) {
                    it.is_active = isActive.isChecked
                }
                if(it!= null){
                    it.units = units.text.toString()
                }
                if(it!=null){
                    it.description = description.text.toString()
                }
            }
            updateViewModel.updateProduct()
            Toast.makeText(context,"Update was successful!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_myDetailFragment)
        }


        return view
    }
}