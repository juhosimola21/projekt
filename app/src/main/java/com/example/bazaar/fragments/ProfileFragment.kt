package com.example.bazaar.fragments

import android.os.Bundle
import android.os.UserHandle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar.MyApplication
import com.example.bazaar.R
import com.example.bazaar.adapters.DataAdapter
import com.example.bazaar.model.Product
import com.example.bazaar.model.User
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.ProfileViewModel
import com.example.bazaar.viewmodels.ProfileViewModelFactory


class ProfileFragment : Fragment() {
    lateinit var profileViewModel: ProfileViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProfileViewModelFactory(Repository())
        profileViewModel = ViewModelProvider(requireActivity(), factory).get(ProfileViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_profile, container, false)
        val email = view.findViewById<TextView>(R.id.emailTextView) //lekerem az xml-bol a TextViewt
        val username = view.findViewById<TextView>(R.id.usernameTextView)
        val username2 = view.findViewById<TextView>(R.id.name_layout)
        val phoneNumber = view.findViewById<TextView>(R.id.phoneNumberTextView)
        val button = view.findViewById<Button>(R.id.settingsButton)

        profileViewModel.user.value!!.username = MyApplication.username
        username.text = MyApplication.username
        username2.text = MyApplication.username
        profileViewModel.getProfile()
        profileViewModel.user.observe(viewLifecycleOwner){

            email.text = it.email
            phoneNumber.text = it.phone_number
        }

        button.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_profileUpdateFragment)
        }
        return view
    }

}