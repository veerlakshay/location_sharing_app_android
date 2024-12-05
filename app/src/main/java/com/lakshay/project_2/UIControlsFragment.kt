package com.lakshay.project_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class UIControlsFragment : Fragment() {

    private lateinit var placeholderText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the fragment's layout
        val view: View = inflater.inflate(R.layout.fragment_ui_controls, container, false)

        // Initialize buttons and the TextView for the message
        val btnMap: Button = view.findViewById(R.id.btnMap)
        val btnEmail: Button = view.findViewById(R.id.btnEmail)
        val btnSms: Button = view.findViewById(R.id.btnSms)
        placeholderText = view.findViewById(R.id.placeholderText)

        // Set onClickListeners for buttons
        btnMap.setOnClickListener {
            // Hide the placeholder text and add MapFragment dynamically
            placeholderText.visibility = View.GONE  // Hide the placeholder text

            // Add MapFragment to the container above buttons
            val mapFragment = MapFragment()
            childFragmentManager.beginTransaction()
                .replace(R.id.mapFragmentContainer, mapFragment)
                .commit()
        }

        btnEmail.setOnClickListener {
            // Handle Email Fragment with back stack support
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EmailFragment())
                .addToBackStack(null) // Add to back stack
                .commit()
        }

        btnSms.setOnClickListener {
            // Handle SMS Fragment with back stack support
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SmsFragment())
                .addToBackStack(null) // Add to back stack
                .commit()
        }

        return view
    }
}
