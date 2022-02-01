package com.example.bazaar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.bazaar.R
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.ProfileViewModel
import com.example.bazaar.viewmodels.ProfileViewModelFactory


class OtherProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProfileViewModelFactory(Repository())
        viewModel = ViewModelProvider(requireActivity(),factory).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View =  inflater.inflate(R.layout.fragment_other_profile, container, false)

        val name = view.findViewById<TextView>(R.id.name_layout)
        val email = view.findViewById<TextView>(R.id.emailTextView)
        val username = view.findViewById<TextView>(R.id.usernameTextView)
        val phone = view.findViewById<TextView>(R.id.phoneNumberTextView)
        viewModel.getProfile()
        viewModel.user.observe(viewLifecycleOwner){
            email.text = it.email
            name.text = it.username
            username.text = it.username
            phone.text = it.phone_number
        }
        return view
    }
}