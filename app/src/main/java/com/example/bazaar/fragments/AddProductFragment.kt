package com.example.bazaar.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar.MyApplication
import com.example.bazaar.R
import com.example.bazaar.adapters.DataAdapter
import com.example.bazaar.repository.Repository
import com.example.bazaar.utils.Constants.ERROR
import com.example.bazaar.viewmodels.AddProductViewModel
import com.example.bazaar.viewmodels.AddProductViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch


class AddProductFragment : Fragment() {
    lateinit var addProductViewModel: AddProductViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: DataAdapter

    lateinit var filepath: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = AddProductViewModelFactory(this.requireContext(), Repository())
        addProductViewModel = ViewModelProvider(this, factory).get(AddProductViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_add_product, container, false)

        val price = resources.getStringArray(R.array.price)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_menu, price)

        var k = layout.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        k.setAdapter(arrayAdapter)


        val amount = resources.getStringArray(R.array.amount)
        val arrayAdapter2 = ArrayAdapter(requireContext(), R.layout.drop_down_menu, amount)

        var k2 = layout.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView_2)
        k2.setAdapter(arrayAdapter2)

        Log.d("xxx", "Drop-down listak megvannak")

        //adatok lekerese

        //kep
        val button_image: FloatingActionButton = layout.findViewById(R.id.floatingActionButton)

        button_image.setOnClickListener {
            startFileChooser()
        }

        //inactive/active
        val switch: Switch = layout.findViewById(R.id.switch1)

        //adatok
        val view:View =  inflater.inflate(R.layout.fragment_add_product, container, false)
        val title: TextInputEditText = layout.findViewById(R.id.textInputEdit_title)
        val editText_price: TextInputEditText = layout.findViewById(R.id.textInputEdit_price)
        val editText_amount: TextInputEditText = layout.findViewById(R.id.textInputEdit_amount)
        val editText_description: TextInputEditText = layout.findViewById(R.id.textInputEdit_description)
        val dropDown1: AutoCompleteTextView = layout.findViewById(R.id.autoCompleteTextView)
        val dropDown2: AutoCompleteTextView = layout.findViewById(R.id.autoCompleteTextView_2)

        //ha kozze teszem
        val button: Button = layout.findViewById(R.id.button_launch_my_fair)
        button.setOnClickListener {
            addProductViewModel.product.value.let {
                if (it != null) {
                    if(title.text.toString() == "")
                    {
                        val helperText: TextInputLayout = layout.findViewById(R.id.textInputLayout_title)
                        helperText.helperText = "Your fair needs to have a title"
                        ERROR = 3
                    }
                    else
                    {
                        it.title = title.text.toString()
                        Log.d("xxx", "Title -> String: ${title.text.toString()}")
                    }

                    if(editText_price.text.toString() == "")
                    {
                        val helperText: TextInputLayout = layout.findViewById(R.id.textInputLayout_price)
                        helperText.helperText = "Give your fare a fair price"
                        ERROR = 3
                    }
                    else
                    {
                        it.price_per_unit = editText_price.text.toString()
                        Log.d("xxx", "Price -> String: ${editText_price.text.toString()}")
                    }

                    if(editText_amount.text.toString() == "")
                    {
                        val helperText: TextInputLayout = layout.findViewById(R.id.textInputLayout_amount)
                        helperText.helperText = "Select an amount"
                        ERROR = 3
                    }
                    else
                    {
                        it.units = editText_amount.text.toString()
                        Log.d("xxx", "Amount -> String: ${editText_amount.text.toString()}")
                    }

                    if(editText_description.text.toString() == "")
                    {
                        val helperText: TextInputLayout = layout.findViewById(R.id.textInputLayout_description)
                        helperText.helperText = "Describe your product"
                        ERROR = 3
                    }
                    else
                    {
                        it.description = editText_description.text.toString()
                        Log.d("xxx", "Description -> String: ${editText_description.text.toString()}")
                    }

                    it.price_type = dropDown1.text.toString()
                    Log.d("xxx", "price_type -> String: ${dropDown1.text.toString()}")

                    it.amount_type = dropDown2.text.toString()
                    Log.d("xxx", "amount_type -> String: ${dropDown2.text.toString()}")

                    it.is_active = switch.isActivated
                    Log.d("xxx", "is_active -> Boolean: ${switch.isActivated}")
                }
            }

            if(ERROR != 3) {
                lifecycleScope.launch {
                    addProductViewModel.addProduct()
                    if (ERROR != 2) {
                        Toast.makeText(context,"You have successfully added an item!", Toast.LENGTH_SHORT).show()
                        ERROR = 4 //most mar nezhetem a reszleteket
                    } else {
                        Toast.makeText(context,"Failed to add an item!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else
            {
                Toast.makeText(context,"Failed to add an item!", Toast.LENGTH_SHORT).show()
            }
        }

        val button2: Button = layout.findViewById(R.id.button_preview_my_fair)
        button2.setOnClickListener {
            if(ERROR == 4)
            {
                findNavController().navigate(R.id.action_addProductFragment_to_myDetailFragment)
            }
            else
            {
                Toast.makeText(context,"Firstly launch your fare!", Toast.LENGTH_SHORT).show()
            }
        }

        return layout
    }

    private fun startFileChooser() {
        var i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i, "Choose Picture!"), 111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == Activity.RESULT_OK && data != null)
        {
            filepath = data.data!!
        }
    }
}