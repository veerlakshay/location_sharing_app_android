package com.lakshay.project_2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class EmailFragment : Fragment() {

    private lateinit var emailAddress: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_email, container, false)
        emailAddress = view.findViewById(R.id.emailAddress)
        val sendEmailButton = view.findViewById<Button>(R.id.sendEmailButton)

        // Load saved email address
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        emailAddress.setText(sharedPreferences.getString("lastEmail", ""))

        sendEmailButton.setOnClickListener {
            sendEmail()
        }
        return view
    }

    private fun sendEmail() {
        val email = emailAddress.text.toString()
        if (email.isBlank()) {
            Toast.makeText(requireContext(), "Enter a valid email address", Toast.LENGTH_SHORT).show()
            return
        }

        // Save email address
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("lastEmail", email)
            apply()
        }

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Current Location")
        intent.putExtra(Intent.EXTRA_TEXT, "Here is my current location: Latitude: X, Longitude: Y, Address: Z")
        startActivity(Intent.createChooser(intent, "Choose an Email client"))
    }
}
