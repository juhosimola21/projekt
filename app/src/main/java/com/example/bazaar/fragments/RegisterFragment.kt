package com.example.bazaar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bazaar.R
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.RegisterViewModel
import com.example.bazaar.viewmodels.RegisterViewModelFactory
import kotlinx.coroutines.launch


class RegisterFragment : Fragment() {
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = RegisterViewModelFactory(this.requireContext(), Repository())
        registerViewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register2, container, false)
        val editText1: EditText = view.findViewById(R.id.edittext_name_register_fragment)
        val editText2: EditText = view.findViewById(R.id.edittext_password_register_fragment)
        val editText3: EditText = view.findViewById(R.id.edittext_email_register_fragment)
        val editText4: EditText = view.findViewById(R.id.edittext_phone_number_register_fragment)
        val TextView :TextView = view.findViewById(R.id.edittext_log_in_register_fragment)
        val button: Button = view.findViewById(R.id.button_register_fragment)
        button.setOnClickListener {
            registerViewModel.user.value.let {
                if (it != null) {
                    it.username = editText1.text.toString()
                }
                if (it != null) {
                    it.password = editText2.text.toString()
                }
                if (it != null) {
                    it.email = editText3.text.toString()
                }
                if (it != null) {
                    it.phone_number = editText4.text.toString()
                }

            }
            lifecycleScope.launch {
                registerViewModel.register()
            }

        }
        registerViewModel.token.observe(viewLifecycleOwner){
            Log.d("xxx", "navigate to login")
            findNavController().navigate(R.id.action_registerFragment_to_listFragment)
        }
        TextView.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        return view
    }
}


