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
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar.R
import com.example.bazaar.MyApplication
import com.example.bazaar.adapters.DataAdapter
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.ProfileUpdateViewModel
import com.example.bazaar.viewmodels.ProfileUpdateViewModelFactory
import com.example.bazaar.viewmodels.ProfileViewModel
import com.example.bazaar.viewmodels.ProfileViewModelFactory


class ProfileUpdateFragment : Fragment() {
    private lateinit var settingsViewModel: ProfileUpdateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProfileUpdateViewModelFactory(Repository())
        settingsViewModel =
            ViewModelProvider(requireActivity(), factory).get(ProfileUpdateViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_profile_update, container, false)

        val email = view.findViewById<EditText>(R.id.editTextTextEmailAddress4)
        val username = view.findViewById<EditText>(R.id.editTextTextUsername)
        val phone = view.findViewById<EditText>(R.id.editTextPhone)
        val button = view.findViewById<Button>(R.id.buttonOfSettings)
        val name = view.findViewById<TextView>(R.id.textView8)

        name.text = MyApplication.username
        button.setOnClickListener {
            settingsViewModel.user.value.let {
                if (it != null) {
                    it.username = username.text.toString()
                }
                if (it != null) {
                    it.email = email.text.toString()
                }
                if (it != null) {
                    it.phone_number = phone.text.toString()
                }
            }
            settingsViewModel.updateUser()

            Toast.makeText(context,"Update was successful!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_profileUpdateFragment_to_profileFragment)
        }


        return view
    }
}