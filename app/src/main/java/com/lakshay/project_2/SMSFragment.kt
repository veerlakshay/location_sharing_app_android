package com.lakshay.project_2

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class SmsFragment : Fragment() {

    private lateinit var phoneNumber: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sms, container, false)
        phoneNumber = view.findViewById(R.id.phoneNumber)
        val sendSmsButton = view.findViewById<Button>(R.id.sendSmsButton)

        // Load saved phone number
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        phoneNumber.setText(sharedPreferences.getString("lastPhoneNumber", ""))

        sendSmsButton.setOnClickListener {
            sendSms()
        }
        return view
    }

    private fun sendSms() {
        val phone = phoneNumber.text.toString()
        if (phone.isBlank()) {
            Toast.makeText(requireContext(), "Enter a valid phone number", Toast.LENGTH_SHORT).show()
            return
        }

        // Save phone number
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("lastPhoneNumber", phone)
            apply()
        }

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("sms:$phone")
        intent.putExtra("sms_body", "Here is my current location: Latitude: X, Longitude: Y, Address: Z")
        startActivity(intent)
    }
}

