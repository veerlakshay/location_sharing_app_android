package com.lakshay.project_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load UIControlsFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, UIControlsFragment())
            .commit()
    }
}


